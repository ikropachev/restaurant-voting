package org.ivan_kropachev.restaurant_voting.model;

public class Dish extends AbstractNamedEntity {

    private int restaurantId;
    private int price;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.id, d.name, d.restaurantId, d.price);
    }

    public Dish(Integer id, String name, int restaurantId, int price) {
        super(id, name);
        this.restaurantId = restaurantId;
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
