package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.CheckTimeUtil.checkTime;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote save(int userId, int restaurantId, LocalTime currentTime) {
        Vote previous = repository.getByUserIdAndDate(userId, LocalDate.now());
        if (previous == null) {
            return repository.save(userId, restaurantId);
        } else {
            checkTime(currentTime);
            return repository.update(previous, restaurantId);
        }
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }
}
