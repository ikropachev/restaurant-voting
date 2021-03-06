package org.ikropachev.voting.web.restaurant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ikropachev.voting.View;
import org.ikropachev.voting.model.Restaurant;
import org.ikropachev.voting.service.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for restaurants from admin")
public class AdminRestaurantController {
    private static final Logger log = getLogger(AdminRestaurantController.class);

    static final String REST_URL = "/rest/admin/restaurants";
    public static final String RESTAURANT1_ID_STR = "100000";

    @Autowired
    private RestaurantService service;

    @GetMapping
    @ApiOperation(value = "View a list of all restaurants")
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View a restaurant by id")
    public Restaurant get(@PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) int id) {
        log.info("get restaurant with id {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a restaurant")
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a restaurant by id")
    public void delete(@PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a restaurant by id")
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant,
                       @PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) int id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        restaurant.setId(id);
        service.update(restaurant);
    }
}
