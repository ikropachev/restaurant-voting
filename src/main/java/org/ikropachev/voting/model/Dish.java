package org.ikropachev.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
public class Dish extends AbstractNamedEntity {

    //@NotNull
    @Column(name = "price", nullable = false)
    @ApiModelProperty(example = "10")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    @ApiModelProperty(hidden = true, readOnly = true)
    private Menu menu;

    public Dish() {
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
        this.menu = null;
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
