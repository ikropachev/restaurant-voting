package org.ikropachev.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(name = "vote_user_id_date_idx", columnNames = {"user_id", "date"})})
public class Vote extends AbstractBaseEntity {

    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @NotNull
    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDate date;

    public Vote() {

    }

    public Vote(Vote v) {
        this(v.id, v.userId, v.restaurantId, v.date);
    }

    public Vote(Integer id, int userId, int restaurantId, LocalDate date) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
