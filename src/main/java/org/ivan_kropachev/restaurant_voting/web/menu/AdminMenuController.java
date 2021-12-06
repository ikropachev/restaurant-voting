package org.ivan_kropachev.restaurant_voting.web.menu;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.web.dish.AdminDishController;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController extends AbstractMenuController {
    private static final Logger LOG = getLogger(AdminMenuController.class);

    static final String REST_URL = "/rest/admin/menues";

    @Override
    @GetMapping
    public List<Menu> getAll() {
        return super.getAll();
    }

    //@Override
    //@GetMapping
    //public List<Menu> getAllByDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    //LOG.info("getAll menues by date {}", date);
    //    return super.getAllByDate(date);
    //}

    @Override
    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllByDateAndRestaurantId(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                  @PathVariable Integer restaurantId) {
        LOG.info("getAll menues by date {} for restaurant {}", date, restaurantId);
        return super.getAllByDateAndRestaurantId(date, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable Integer restaurantId) {
        menu.setId(null);
        menu.setRestaurant(null);
        LOG.info("create {} for restaurant {}", menu, restaurantId);
        Menu created = super.create(menu, restaurantId);;

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, @PathVariable Integer restaurantId) {
        LOG.info("delete menu {} for restaurant {}", id, restaurantId);
        super.delete(id, restaurantId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @PathVariable Integer id) {
        menu.setId(id);
        LOG.info("update {} with id={}", menu, id);
        super.update(menu, id);
    }
}
