package org.ivan_kropachev.restaurant_voting.model;

public class Restaurant extends AbstractNamedEntity {
    //private int id;
    private String name;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public boolean isNew() {
        return this.id == null;
    }

    //public int getId() {
    //    return id;
    //}

    //public void setId(int id) {
    //    this.id = id;
    //}

    //public String getName() {
    //    return name;
    //}

    //public void setName(String name) {
    //    this.name = name;
    //}

    //@Override
    //public String toString() {
    //    return "Restaurant{" +
    //            "id=" + id +
    //            ", name='" + name + '\'' +
    //            '}';
    //}
}
