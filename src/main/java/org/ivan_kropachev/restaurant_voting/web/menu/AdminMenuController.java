package org.ivan_kropachev.restaurant_voting.web.menu;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.service.DishService;
import org.ivan_kropachev.restaurant_voting.util.View;
import org.ivan_kropachev.restaurant_voting.web.dish.AdminDishController;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    static final String REST_URL = "/rest/admin/menus";

    @PostMapping(value = "/{restaurantId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable Integer restaurantId) {
        //LOG.info(" {} ", menu.getDishes());
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        }
        LOG.info("create {} for restaurant {}", menu, restaurantId);
        Menu created = super.create(menu, restaurantId);
        //menu.getDishes().forEach(dish -> dishService.create(dish, created.getId()));
        LOG.info("create {} for restaurant {}", created, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menu/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping
    public List<Menu> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "date/{date}")
    public List<Menu> getAllByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
    LOG.info("get all menus by date {}", date);
        return super.getAllByDate(date);
    }

    @Override
    @GetMapping(value = "/{restaurantId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllByDateAndRestaurantId(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                  @PathVariable Integer restaurantId) {
        LOG.info("getAll menus by date {} for restaurant {}", date, restaurantId);
        return super.getAllByDateAndRestaurantId(date, restaurantId);
    }

    @Override
    @DeleteMapping("{restaurantId}/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, @PathVariable Integer restaurantId) {
        LOG.info("delete menu {} for restaurant {}", id, restaurantId);
        super.delete(id, restaurantId);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu,
                           @PathVariable Integer restaurantId, @PathVariable Integer id) {
        menu.setId(id);
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        }
        LOG.info("update {} with id={} for restaurant {}", menu, menu.getId(), restaurantId);
        super.update(menu, restaurantId, id);
    }

    @PutMapping(value = "/{restaurantId}/menu/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu,
                       @PathVariable Integer restaurantId) {
        //menu.setId(id);
        //if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        //}
        LOG.info("update {} with id={} for restaurant {}", menu, menu.getId(), restaurantId);
        super.updateWithoutId(menu, restaurantId);
    }
}
