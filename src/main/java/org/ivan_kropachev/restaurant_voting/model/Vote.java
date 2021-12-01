package org.ivan_kropachev.restaurant_voting.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {

    private int userId;
    private int restaurantId;
    private LocalDateTime dateTime;

    public Vote() {

    }

    public Vote(Vote v) {
        this(v.id, v.userId, v.restaurantId, v.dateTime);
    }

    public Vote(Integer id, int userId, int restaurantId, LocalDateTime dateTime) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
