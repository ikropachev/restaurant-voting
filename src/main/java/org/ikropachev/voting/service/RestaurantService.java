package org.ikropachev.voting.service;

import org.ikropachev.voting.model.Restaurant;
import org.ikropachev.voting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ikropachev.voting.util.ValidationUtil.checkNew;
import static org.ikropachev.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
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
