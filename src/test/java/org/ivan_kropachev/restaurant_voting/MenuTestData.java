package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.RestaurantTestData.restaurant1;
import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    //https://stackoverflow.com/questions/8746084/string-to-localdate
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final LocalDate DATE = LocalDate.parse("2021-12-03", PATTERN);
    public static final int MENU1_ID = START_SEQ + 8;

    public static final Menu menu1 = new Menu(MENU1_ID, DATE);
    public static final Menu menu2 = new Menu(MENU1_ID + 1, DATE);
    public static final Menu menu3 = new Menu(MENU1_ID + 2, DATE);
    public static final Menu menu4 = new Menu(MENU1_ID + 3, DATE);
    public static final Menu menu5 = new Menu(MENU1_ID + 4, LocalDate.now());
    public static final Menu menu6 = new Menu(MENU1_ID + 5, LocalDate.now());

    //Menus must be sorted by date DESC
    public static final List<Menu> menus = List.of(menu5, menu6, menu1, menu2, menu3, menu4);

    public static Menu getNew() {
        return new Menu(null, restaurant1, LocalDate.now(), new ArrayList<>(Arrays.asList(DishTestData.getNew())));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, restaurant1, DATE, new ArrayList<>(Arrays.asList(DishTestData.getUpdated())));
    }
}
