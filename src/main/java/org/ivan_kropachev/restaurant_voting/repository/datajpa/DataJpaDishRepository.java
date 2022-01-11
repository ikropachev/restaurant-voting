package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDishRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final CrudDishRepository crudDishRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudMenuRepository crudMenuRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Transactional
    public Dish save(Dish dish, int menuId) {
        log.info("save dish {} for menu {}", dish, menuId);
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setMenu(crudMenuRepository.getOne(menuId));
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }
}
