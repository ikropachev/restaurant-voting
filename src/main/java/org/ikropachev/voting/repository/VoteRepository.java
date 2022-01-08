package org.ikropachev.voting.repository;

import org.ikropachev.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(int userId, int restaurantId);

    Vote update(Vote previous, int restaurantId);

    List<Vote> getAllForToday();

    // null if not found
    Vote get(int id);

    Vote getByUserIdAndDate(int userId, LocalDate date);
}
