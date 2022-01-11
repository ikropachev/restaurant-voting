package org.ivan_kropachev.restaurant_voting.web.menu;

import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.service.MenuService;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;
import org.ivan_kropachev.restaurant_voting.web.AbstractControllerTest;
import org.ivan_kropachev.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ivan_kropachev.restaurant_voting.MenuTestData.*;
import static org.ivan_kropachev.restaurant_voting.RestaurantTestData.RESTAURANT1_ID;
import static org.ivan_kropachev.restaurant_voting.TestUtil.userHttpBasic;
import static org.ivan_kropachev.restaurant_voting.UserTestData.admin;
import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminMenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminMenuController.REST_URL + '/';

    @Autowired
    private MenuService service;

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/menus/by-date?date=" + DATE)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1, menu4, menu2, menu3));
    }

    /*
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menus));
    }

     */

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID + "/menus/" + MENU1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID + "/menus/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createWithLocation() throws Exception {
        Menu newMenu = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID + "/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newMenu)));

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newMenu);
    }

    @Test
    void update() throws Exception {
        Menu updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT1_ID + "/menus/" + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MENU_MATCHER.assertMatch(service.get(MENU1_ID, RESTAURANT1_ID), updated);
    }
}
