package org.ikropachev.voting.service;

import org.ikropachev.voting.model.Menu;
import org.ikropachev.voting.repository.datajpa.DataJpaMenuRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.ikropachev.voting.util.ValidationUtil.checkNew;
import static org.ikropachev.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final DataJpaMenuRepository repository;

    public MenuService(DataJpaMenuRepository repository) {
        this.repository = repository;
    }

    public Menu create(Menu menu, int restaurantId) {
        checkNew(menu);
        return repository.save(menu, restaurantId);
    }

    public Menu update(Menu menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Menu> getAll() {
        return repository.getAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public Menu getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id), id);
    }
}
