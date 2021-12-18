package org.ivan_kropachev.restaurant_voting.repository;

import org.ivan_kropachev.restaurant_voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    // null if not found, when updated
    Menu create(Menu menu, int restaurantId);

    Menu update(Menu menu, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);
}
