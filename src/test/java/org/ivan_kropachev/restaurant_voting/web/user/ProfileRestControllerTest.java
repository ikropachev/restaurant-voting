package org.ivan_kropachev.restaurant_voting.web.user;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.service.UserService;
import org.ivan_kropachev.restaurant_voting.to.UserTo;
import org.ivan_kropachev.restaurant_voting.util.UserUtil;
import org.ivan_kropachev.restaurant_voting.web.AbstractControllerTest;
import org.ivan_kropachev.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ivan_kropachev.restaurant_voting.TestUtil.userHttpBasic;
import static org.ivan_kropachev.restaurant_voting.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.ivan_kropachev.restaurant_voting.web.user.ProfileRestController.REST_URL;

public class ProfileRestControllerTest  extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user1));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(service.getAll(), admin, user2, user3);
    }

    @Test
    void register() throws Exception {
        UserTo newTo = new UserTo(null, "New_Test_User", "new-test-email@gmail.com", "NewTestPassword");
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void update() throws Exception {
        UserTo updatedTo = new UserTo(null, "New_Test_User", "new-test-email@gmail.com", "NewTestPassword");
        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user1))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(service.get(USER_ID), UserUtil.updateFromTo(new User(user1), updatedTo));
    }
}
