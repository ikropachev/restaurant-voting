package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class DataJpaRestaurantRepository {

    private final CrudRestaurantRepository repository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.repository = crudRestaurantRepository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
