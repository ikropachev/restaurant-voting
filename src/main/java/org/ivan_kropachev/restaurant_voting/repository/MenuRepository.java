package org.ivan_kropachev.restaurant_voting.repository;

import org.ivan_kropachev.restaurant_voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    // null if not found, when updated
    Menu save(Menu menu, int restaurantId);

    public Menu saveWithoutId(Menu menu, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    void deleteAll();

    // null if not found
    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);

    //List<Menu> getAllByRestaurantId(int Id, int restaurantId);

    List<Menu> getAllByDateAndRestaurantId(LocalDate date, int restaurantId);
}
