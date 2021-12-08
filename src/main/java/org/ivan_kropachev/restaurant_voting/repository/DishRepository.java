package org.ivan_kropachev.restaurant_voting.repository;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    //Dish save(Dish dish);

    public Dish save(Dish dish, int menuId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    public Dish get(int id, int menuId);

    List<Dish> getAll();
}
