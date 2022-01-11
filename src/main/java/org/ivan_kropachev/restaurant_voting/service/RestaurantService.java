package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.repository.datajpa.DataJpaRestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNew;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final DataJpaRestaurantRepository repository;

    public RestaurantService(DataJpaRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }
}
