package org.ivan_kropachev.restaurant_voting.model;

import java.util.Set;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean priveleged; //have or not admin privilegies
    private int restaurantId;   //voted restaurant

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPriveleged() {
        return priveleged;
    }

    public void setPriveleged(boolean priveleged) {
        this.priveleged = priveleged;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", priveleged=" + priveleged +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
