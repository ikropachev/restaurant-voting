package org.ikropachev.voting.service;

import org.ikropachev.voting.model.Dish;
import org.ikropachev.voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.ikropachev.voting.Constants.*;
import static org.ikropachev.voting.DishTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractDishServiceTest extends AbstractServiceTest {
    @Autowired
    protected DishService service;

    @Test
    void delete() {
        service.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Dish created = service.create(getNew(), MENU1_ID);
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId), newDish);
    }

    @Test
    void get() {
        Dish actual = service.get(DISH1_ID);
        DISH_MATCHER.assertMatch(actual, dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getAll() {
        DISH_MATCHER.assertMatch(service.getAll(), dishes);
    }
}
