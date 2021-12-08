package org.ivan_kropachev.restaurant_voting.web.dish;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.web.restaurant.AdminRestaurantController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController extends AbstractDishController {

    static final String REST_URL = "/rest/admin/dishes";

    @Override
    @GetMapping
    public List<Dish> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int menuId) {
        Dish created = super.create(dish, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{menuId}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int menuId) {
        super.update(dish, menuId);
    }


    /*
    @Override
    public List<Dish> getAll() {
        return super.getAll();
    }

    @Override
    public Dish get(int id) {
        return super.get(id);
    }

    @Override
    public Dish create(Dish dish) {
        return super.create(dish);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Dish dish, int id) {
        super.update(dish, id);
    }

     */
}
