package org.ikropachev.voting.repository.datajpa;

import org.ikropachev.voting.model.Menu;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository {

    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        if ((menu.getDate() == null) && !(menu.isNew())) {
            Menu previous = get(menu.getId(), restaurantId);
            menu.setDate(previous.getDate());
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    public boolean delete(int id, int restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        return crudMenuRepository.getAllByDate(date);
    }

    public Menu getWithDishes(int id) {
        return crudMenuRepository.getWithDishes(id);
    }
}
