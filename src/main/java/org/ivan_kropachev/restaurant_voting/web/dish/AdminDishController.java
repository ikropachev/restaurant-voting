package org.ivan_kropachev.restaurant_voting.web.dish;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminDishController extends AbstractDishController {

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
}
