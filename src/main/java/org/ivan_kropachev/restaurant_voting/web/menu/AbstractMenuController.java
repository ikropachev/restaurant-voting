package org.ivan_kropachev.restaurant_voting.web.menu;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.service.DishService;
import org.ivan_kropachev.restaurant_voting.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNew;

public class AbstractMenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    public List<Menu> getAll() {
        log.info("get all menues");
        return service.getAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        log.info("get menu for date{}", date);
        return service.getAllByDate(date);
    }

    public List<Menu> getAllByDateAndRestaurantId(LocalDate date, Integer restaurantId) {
        log.info("get menu for date{} and restaurant{}", date, restaurantId);
        return service.getAllByDateAndRestaurantId(date, restaurantId);
    }

    public Menu create(Menu menu, Integer restaurantId) {
        log.info("create menu {} for restaurant {}", menu, restaurantId);
        checkNew(menu);
        return service.create(menu, restaurantId);
    }

    public void delete(Integer id, Integer restaurantId) {
        log.info("delete menu {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public void deleteAll() {
        log.info("delete all menues");
        service.deleteAll();
    }

    public void update(Menu menu, Integer id) {
        log.info("update menu {} with id {} for restaurant {}", menu, id);
        assureIdConsistent(menu, id);
        service.update(menu, id);
    }
}
