package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import java.util.List;
import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int NOT_FOUND = 100000;
    public static final int TEST_MENU = 100011;
    public static final int DISH1_ID = START_SEQ + 14;

    public static final Dish dish1 = new Dish(DISH1_ID, "Hachapuri po imeretinski", 550);
    public static final Dish dish2 = new Dish(DISH1_ID+1, "Vegan Burger", 150);
    public static final Dish dish3 = new Dish(DISH1_ID+2, "Chicken Burger", 50);
    public static final Dish dish4 = new Dish(DISH1_ID+3, "Meat Shaverma", 100);
    public static final Dish dish5 = new Dish(DISH1_ID+4, "Vegan Shaverma", 150);
    public static final Dish dish6 = new Dish(DISH1_ID+5, "Chicken Shaverma", 50);
    public static final Dish dish7 = new Dish(DISH1_ID+6, "Hachapuri po adzharski", 350);
    public static final Dish dish8 = new Dish(DISH1_ID+7, "Hachapuri po megrelski", 450);
    public static final Dish dish9 = new Dish(DISH1_ID+8, "Meat Burger", 100);

    //Dishes must be sorted by menu_id DESC
    public static final List<Dish> dishes = List.of(dish8, dish6, dish9, dish7, dish1, dish5, dish4, dish3, dish2);

    public static Dish getNew() {
        return new Dish(null, "New_Test_Dish", 65);
    }
}
