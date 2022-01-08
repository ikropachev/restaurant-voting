package org.ikropachev.voting.repository;

import org.ikropachev.voting.model.Dish;

import java.util.List;

public interface DishRepository {

    public Dish save(Dish dish, int menuId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();
}
