package org.ivan_kropachev.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @NotNull
    @Column(name = "price", nullable = false)
    private int price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

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

    public Dish(String name, int restaurant_id, int price) {
        this(null, name, restaurant_id, price);
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
