package org.ivan_kropachev.restaurant_voting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.ivan_kropachev.restaurant_voting.DishTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.ivan_kropachev.restaurant_voting.DishTestData.*;

import org.springframework.beans.factory.annotation.Autowired;

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
        Dish created = service.create(getNew(), TEST_MENU);
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
