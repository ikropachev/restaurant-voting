package org.ikropachev.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(name = "menu_restaurant_id_created_on_idx", columnNames = {"restaurant_id", "created_on"})})
public class Menu extends AbstractBaseEntity {
    private static final String DISH_LIST_STR = "[\n{\n\"id\": \"null\",\n\"name\": \"dish1\",\n\"price\": 10\n},\n" +
            "    {\n\"id\": \"null\",\n\"name\": \"dish2\",\n\"price\": 20\n}\n]";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "created_on", columnDefinition = "timestamp default now()")
    @ApiModelProperty(example = "null", readOnly = true)
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    @ApiModelProperty(position = 3, example = DISH_LIST_STR)
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
    }

    //Constructor for tests with ignoring fields
    public Menu(Integer id, LocalDate date) {
        super(id);
        this.restaurant = null;
        this.date = date;
        this.dishes = null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
