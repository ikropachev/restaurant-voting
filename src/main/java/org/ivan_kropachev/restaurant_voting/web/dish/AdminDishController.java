package org.ivan_kropachev.restaurant_voting.web.dish;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description="Operations for dishes from admin")
public class AdminDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/dishes";

    @Autowired
    private DishService service;

    @GetMapping
    @ApiOperation(value = "View a list of all dishes")
    public List<Dish> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View a dish by id")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a dish for menu")
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @RequestParam(value = "menu-id") int menuId) {
        log.info("create dish with id {} for menu with id {}", dish, menuId);
        Dish created = service.create(dish, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a dish by id")
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {}", id);
        service.delete(id);
    }
}
