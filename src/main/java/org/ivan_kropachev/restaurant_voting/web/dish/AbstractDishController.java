package org.ivan_kropachev.restaurant_voting.web.dish;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.service.DishService;
import org.ivan_kropachev.restaurant_voting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNew;

public class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public List<Dish> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }

    public Dish get(int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }

    public Dish create(Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        return service.create(dish);
    }

    public void delete(int id) {
        log.info("delete dish {}", id);
        service.delete(id);
    }

    public void update(Dish dish, int id) {
        log.info("update dish {} with id={}", dish, id);
        assureIdConsistent(dish, id);
        service.update(dish);
    }
}
