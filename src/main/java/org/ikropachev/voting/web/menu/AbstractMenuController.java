package org.ikropachev.voting.web.menu;

import org.ikropachev.voting.model.Menu;
import org.ikropachev.voting.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.ikropachev.voting.util.ValidationUtil.checkNew;

public class AbstractMenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    public Menu get(int id) {
        log.info("get menu with id {}", id);
        return service.getWithDishes(id);
    }

    public List<Menu> getAll() {
        log.info("get all menus");
        return service.getAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        log.info("get all menus by date {}", date);
        return service.getAllByDate(date);
    }

    public Menu create(Menu menu, Integer restaurantId) {
        log.info("create menu {} for restaurant with id {}", menu, restaurantId);
        checkNew(menu);
        return service.create(menu, restaurantId);
    }

    public void delete(Integer id, Integer restaurantId) {
        log.info("delete menu with id {} for restaurant with id {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public void update(Menu menu, Integer restaurantId) {
        log.info("update menu {} for restaurant with id {}", menu, restaurantId);
        service.update(menu, restaurantId);
    }
}
