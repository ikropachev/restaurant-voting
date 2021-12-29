package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Restaurant;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int NOT_FOUND = 100010;
    public static final int RESTAURANT1_ID = START_SEQ;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "BarZero");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Burgers");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "HappyShaverma");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "Suluguni");

    //Restaurants must be sorted by name
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3, restaurant4);

    public static Restaurant getNew() {
        return new Restaurant(null, "New_Test_Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated_Test_Restaurant");
    }
}
