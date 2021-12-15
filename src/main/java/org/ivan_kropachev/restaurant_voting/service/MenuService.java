package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.repository.DishRepository;
import org.ivan_kropachev.restaurant_voting.repository.MenuRepository;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository repository;
    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu create(Menu menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void update(Menu menu, int restaurantId) {
        checkNotFoundWithId(repository.save(menu, restaurantId), menu.getId());
    }

    public void updateWithoutId(Menu menu, int restaurantId) {
        repository.saveWithoutId(menu, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Menu> getAll() {
        return repository.getAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public List<Menu> getAllByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return repository.getAllByDateAndRestaurantId(date, restaurantId);
    }
}
