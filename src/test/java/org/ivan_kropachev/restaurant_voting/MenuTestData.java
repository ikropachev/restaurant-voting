package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.Constants.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT1_ID, "BarZero");
    public static final Dish DISH = new Dish(null, "New_Update_Test_Dish", 55);

    public static final Menu menu1 = new Menu(MENU1_ID, DATE);
    public static final Menu menu2 = new Menu(MENU1_ID + 1, DATE);
    public static final Menu menu3 = new Menu(MENU1_ID + 2, DATE);
    public static final Menu menu4 = new Menu(MENU1_ID + 3, DATE);
    public static final Menu menu5 = new Menu(MENU1_ID + 4, LocalDate.now());
    public static final Menu menu6 = new Menu(MENU1_ID + 5, LocalDate.now());

    //Menus must be sorted by date DESC
    public static final List<Menu> menus = List.of(menu5, menu6, menu1, menu2, menu3, menu4);

    public static Menu getNew() {
        return new Menu(null, RESTAURANT, LocalDate.now(), new ArrayList<>(Arrays.asList(new Dish(null, "New_Test_Dish", 65))));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, RESTAURANT, DATE, new ArrayList<>(Arrays.asList(DISH)));
    }
}
