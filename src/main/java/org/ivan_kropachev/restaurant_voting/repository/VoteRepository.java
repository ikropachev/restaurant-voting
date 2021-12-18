package org.ivan_kropachev.restaurant_voting.repository;

import org.ivan_kropachev.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(int userId, int restaurantId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    List<Vote> getAll();
}
