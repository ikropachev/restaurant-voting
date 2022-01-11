package org.ivan_kropachev.restaurant_voting.web.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ivan_kropachev.restaurant_voting.View;
import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.web.restaurant.AdminRestaurantController.RESTAURANT1_ID_STR;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for menus from admin")
public class AdminMenuController extends AbstractMenuController {
    private static final Logger log = getLogger(AdminMenuController.class);

    static final String REST_URL = "/rest/admin/restaurants";
    public static final String MENU1_ID_STR = "100008";
    protected static final String DATE_STR = "2021-12-03";

    @Override
    @GetMapping(value = "/menus/by-date")
    @ApiOperation(value = "View a list of all menus by date")
    public List<Menu> getAllByDate(@Nullable @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   @ApiParam(value = "null for current date", example = DATE_STR, required = false) LocalDate date) {
        log.info("get all menus by date {}", date);
        if (date == null) {
            date = LocalDate.now();
            log.info("set date {}", date);
        }
        return super.getAllByDate(date);
    }

    @Override
    @DeleteMapping("/{restaurantId}/menus/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a menu by restaurant id and menu id")
    public void delete(@PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @PathVariable @ApiParam(example = MENU1_ID_STR, required = true) Integer menuId) {
        log.info("delete menu with id {} for restaurant with id {}", menuId, restaurantId);
        super.delete(menuId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a menu")
    public ResponseEntity<Menu> createWithLocation(@Validated(View.Web.class) @RequestBody
                                                   @ApiParam(value = "\"restaurant\" field in request body may absent, " +
                                                           "it doesn't use in request.") Menu menu,
                                                   @PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true)
                                                           Integer restaurantId) {
        log.info("create {} for restaurant {}", menu, restaurantId);

        //Fix bug with lost dishes if dish_id not null (dishes always new)
        List<Dish> dishes = menu.getDishes();
        if (dishes != null) {
            dishes.forEach(dish -> dish.setId(null));
        }

        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
            log.info("set date {} for menu", menu.getDate());
        }
        Menu created = super.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menus/{menuId}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menus/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a menu for a restaurant")
    public void update(@Validated(View.Web.class) @RequestBody Menu menu,
                       @PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @PathVariable @ApiParam(example = MENU1_ID_STR, required = true) Integer menuId) {
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        menu.setId(menuId);

        //Fix bug with lost dishes if dish_id not null (dishes always new, previous will delete)
        List<Dish> dishes = menu.getDishes();
        if (dishes != null) {
            dishes.forEach(dish -> dish.setId(null));
        }
        super.update(menu, restaurantId);
    }
}
