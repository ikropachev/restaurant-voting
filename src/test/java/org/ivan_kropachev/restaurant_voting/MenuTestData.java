package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.ivan_kropachev.restaurant_voting.DishTestData.dishes;
import static org.ivan_kropachev.restaurant_voting.RestaurantTestData.restaurants;
import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    //https://stackoverflow.com/questions/8746084/string-to-localdate
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final int NOT_FOUND = 100000;
    public static final int MENU1_ID = START_SEQ + 8;
    public static final int RESTAURANT1_ID = 100001;
    public static final Restaurant RESTAURANT = new Restaurant (RESTAURANT1_ID, "Burgers");
    public static final LocalDate DATE = LocalDate.parse("2021-12-03", PATTERN);
    public static final Dish DISH1 = new Dish(null, "New_Test_Dish", 65);
    public static final Dish DISH2 = new Dish(null, "New_Update_Test_Dish", 55);
    public static final Dish DISH3 = new Dish(null, "New_Update_Test_Dish", 55);

/*
    public static final Menu menu1 = new Menu(MENU1_ID, restaurants.get(1), LocalDate.parse("2021-12-03", PATTERN),
            new HashSet<Dish>(Arrays.asList(dishes.get(8), dishes.get(7))));
    public static final Menu menu2 = new Menu(MENU1_ID+1, restaurants.get(2), LocalDate.parse("2021-12-03", PATTERN),
            new HashSet<Dish>(Arrays.asList(dishes.get(1), dishes.get(5))));
    public static final Menu menu3 = new Menu(MENU1_ID+2, restaurants.get(3), LocalDate.parse("2021-12-03", PATTERN),
            new HashSet<Dish>(Arrays.asList(dishes.get(4), dishes.get(3))));
    public static final Menu menu4 = new Menu(MENU1_ID+3, restaurants.get(1), LocalDate.parse("2021-12-05", PATTERN),
            new HashSet<Dish>(Arrays.asList(dishes.get(2))));
    public static final Menu menu5 = new Menu(MENU1_ID+4, restaurants.get(2), LocalDate.now(),
            new HashSet<Dish>(Arrays.asList(dishes.get(1))));
    public static final Menu menu6 = new Menu(MENU1_ID+5, restaurants.get(3), LocalDate.now(),
            new HashSet<Dish>(Arrays.asList(dishes.get(0))));
 */

    public static final Menu menu1 = new Menu(MENU1_ID, LocalDate.parse("2021-12-03", PATTERN));
    public static final Menu menu2 = new Menu(MENU1_ID+1, LocalDate.parse("2021-12-03", PATTERN));
    public static final Menu menu3 = new Menu(MENU1_ID+2, LocalDate.parse("2021-12-03", PATTERN));
    public static final Menu menu4 = new Menu(MENU1_ID+3, LocalDate.parse("2021-12-05", PATTERN));
    public static final Menu menu5 = new Menu(MENU1_ID+4, LocalDate.now());
    public static final Menu menu6 = new Menu(MENU1_ID+5, LocalDate.now());

    //Menus must be sorted by date DESC
    public static final List<Menu> menus = List.of(menu5, menu6, menu4, menu1, menu2, menu3);

    public static Menu getNew() {
        return new Menu(null, RESTAURANT, LocalDate.now(), new HashSet<>(Arrays.asList(DISH1)));
    }

    //Method with empty dish set. Needed for run tests altogether.
    public static Menu getNewWithoutDishes() {
        return new Menu(null, RESTAURANT, LocalDate.now(), new HashSet<>(Arrays.asList()));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, RESTAURANT, DATE, new HashSet<>(Arrays.asList(DISH2)));
    }


}
