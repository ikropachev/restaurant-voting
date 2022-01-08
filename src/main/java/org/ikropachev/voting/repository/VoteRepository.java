package org.ikropachev.voting.repository;

import org.ikropachev.voting.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote save(int userId, int restaurantId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    List<Vote> getAll();
}
