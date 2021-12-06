package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.cache.annotation.CacheEvict;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository
public class DataJpaDishRepository {
    private final CrudDishRepository repository;
    public DataJpaDishRepository(CrudDishRepository crudDishRepository) {
        this.repository = crudDishRepository;
    }
    @PersistenceContext
    private EntityManager em;

    @CacheEvict(value = "dishes", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }
}
