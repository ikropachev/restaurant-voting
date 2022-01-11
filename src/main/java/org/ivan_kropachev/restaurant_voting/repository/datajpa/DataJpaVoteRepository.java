package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository {

    private final CrudVoteRepository crudVoteRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote(null, userId, restaurantId, LocalDate.now());
        return crudVoteRepository.save(vote);
    }

    public Vote update(Vote vote, int restaurantId) {
        vote.setRestaurantId(restaurantId);
        return crudVoteRepository.save(vote);
    }

    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        return crudVoteRepository.getByUserIdAndDate(userId, date);
    }
}
