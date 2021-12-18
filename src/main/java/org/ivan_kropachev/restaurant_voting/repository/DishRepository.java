package org.ivan_kropachev.restaurant_voting.repository;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishRepository {

    public Dish save(Dish dish, int menuId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();
}
