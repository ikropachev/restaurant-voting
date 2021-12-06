package org.ivan_kropachev.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.web.restaurant.AbstractRestaurantController;
import org.ivan_kropachev.restaurant_voting.web.user.AdminUserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController  {

    private static final Logger LOG = getLogger(AdminRestaurantController.class);

    static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping
    public List<Restaurant> getAll() {

        LOG.info("getAll restaurants");
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {

        LOG.info("get restaurant {}", id);
        return super.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        LOG.info("create restaurant");
        Restaurant created = super.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        LOG.info("delete restaurant {}", id);
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        LOG.info("update {}", restaurant);
        restaurant.setId(id);
        LOG.info("update {} with id={}", restaurant, id);
        super.update(restaurant, id);
    }


    /*
    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        super.update(restaurant, id);
    }

     */
}
