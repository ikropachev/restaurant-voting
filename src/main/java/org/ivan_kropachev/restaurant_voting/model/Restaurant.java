package org.ivan_kropachev.restaurant_voting.model;

public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        this(null, name);
    }
}
