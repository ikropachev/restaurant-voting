package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.ivan_kropachev.restaurant_voting.MenuTestData.NOT_FOUND;
import static org.ivan_kropachev.restaurant_voting.MenuTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractMenuServiceTest extends AbstractServiceTest {
    @Autowired
    protected MenuService service;

    @Test
    void delete() {
        service.delete(MENU1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    void create() {
        Menu created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newMenu);
    }

    @Test
    void update() {
        Menu updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MENU_MATCHER.assertMatch(service.get(MENU1_ID, RESTAURANT1_ID), getUpdated());
    }

    @Test
    void get() {
        Menu actual = service.get(MENU1_ID, RESTAURANT1_ID);
        MENU_MATCHER.assertMatch(actual, menu1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    void getAll() {
        MENU_MATCHER.assertMatch(service.getAll(), menus);
    }
}
