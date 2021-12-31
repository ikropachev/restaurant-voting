package org.ivan_kropachev.restaurant_voting.web.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.util.exception.IllegalRequestDataException;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.Constants.*;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for menus from admin")
public class AdminMenuController extends AbstractMenuController {
    private static final Logger log = getLogger(AdminMenuController.class);

    static final String REST_URL = "/rest/admin/menus";

    @Override
    @GetMapping
    @ApiOperation(value = "View a list of all menus")
    public List<Menu> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "by-date")
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
    @DeleteMapping("restaurant/{restaurantId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a menu by restaurant id and menu id")
    public void delete(@PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @RequestParam(value = "menu-id") @ApiParam(example = MENU1_ID_STR, required = true) Integer menuId) {
        log.info("delete menu with id {} for restaurant with id {}", menuId, restaurantId);
        super.delete(menuId, restaurantId);
    }

    @PostMapping(value = "restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a menu")
    public ResponseEntity<Menu> createWithLocation(@RequestBody @ApiParam(value = "\"restaurant\" field in request body may absent, " +
                                                           "it doesn't use in request.") Menu menu,
                                                   @PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId) {
        log.info("create {} for restaurant {}", menu, restaurantId);
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
            log.info("set date {} for menu", menu.getDate());
        }
        Menu created = super.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{menu-id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "restaurant/{restaurantId}/date/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a menu for a restaurant for a certain date")
    public void update(@RequestBody Menu menu,
                       @PathVariable @ApiParam(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @Nullable @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           @ApiParam(value = "null for current date", example = DATE_STR, required = false) LocalDate date) {
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        if (date == null) {
            date = LocalDate.now();
        }
        //if (!menu.getDate().equals(date)) {
        //    throw new IllegalRequestDataException("Date from request body doesn't match date from request parameter.");
        //}
        if (menu.getDate() == null) {
            log.info("set date {} for menu {}", date, menu);
            menu.setDate(date);
        }
        super.update(menu, restaurantId);
    }
}
