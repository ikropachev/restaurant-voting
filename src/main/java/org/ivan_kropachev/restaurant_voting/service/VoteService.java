package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.repository.DishRepository;
import org.ivan_kropachev.restaurant_voting.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote) {
        return repository.save(vote);
    }

    public Vote create(int userId, int restaurantId) {
        return repository.save(userId, restaurantId);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }

    public void update(Vote vote) {
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }
}
