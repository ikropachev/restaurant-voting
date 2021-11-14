package org.ivan_kropachev.restaurant_voting.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String name;
    private String email;
    private String password;
    private String privileged; //have or not admin privilegies
    private LocalDateTime voteDateTime;
    private int restaurantId;   //voted restaurant

    public User() {
    }

    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.privileged, u.voteDateTime, u.restaurantId);
    }

    //public User(Integer id, String name, String email, String password, boolean privileged, LocalDateTime voteDateTime, Integer restaurantId) {
    //    this(id, name, email, password, privileged, voteDateTime, restaurantId);
    //}

    public User(Integer id, String name, String email, String password, String privileged, LocalDateTime voteDateTime, Integer restaurantId) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.privileged = privileged;
        this.voteDateTime = voteDateTime;
        this.restaurantId = restaurantId;
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

    public String getPrivileged() {
        return privileged;
    }

    public void setPrivileged(String privileged) {
        this.privileged = privileged;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isNew() {
        return this.id == null;
    }

    //@Override
    //public String toString() {
    //    return "User{" +
    //            "id=" + id +
    //            ", name='" + name + '\'' +
    //            ", email='" + email + '\'' +
    //            ", password='" + password + '\'' +
    //            ", privileged=" + privileged +
    //            ", voteDateTime=" + voteDateTime +
    //            ", restaurantId=" + restaurantId +
    //            '}';
    //}
}
