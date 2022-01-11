package org.ikropachev.voting.service;

import org.ikropachev.voting.model.Dish;
import org.ikropachev.voting.repository.datajpa.DataJpaDishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ikropachev.voting.util.ValidationUtil.checkNew;
import static org.ikropachev.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    private final DataJpaDishRepository repository;

    public DishService(DataJpaDishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int menuId) {
        checkNew(dish);
        return repository.save(dish, menuId);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Dish> getAll() {
        return repository.getAll();
    }
}
