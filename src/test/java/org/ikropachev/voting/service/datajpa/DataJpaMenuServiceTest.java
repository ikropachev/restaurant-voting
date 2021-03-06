package org.ikropachev.voting.service.datajpa;

import org.ikropachev.voting.model.Menu;
import org.ikropachev.voting.service.AbstractServiceTest;
import org.ikropachev.voting.service.MenuService;
import org.ikropachev.voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.ikropachev.voting.MenuTestData.*;
import static org.ikropachev.voting.RestaurantTestData.RESTAURANT1_ID;
import static org.ikropachev.voting.model.AbstractBaseEntity.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataJpaMenuServiceTest extends AbstractServiceTest {
    @Autowired
    protected MenuService service;

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
    void delete() {
        service.delete(MENU1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, RESTAURANT1_ID));
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
