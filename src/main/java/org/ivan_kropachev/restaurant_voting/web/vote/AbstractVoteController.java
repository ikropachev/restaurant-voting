package org.ivan_kropachev.restaurant_voting.web.vote;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.service.UserService;
import org.ivan_kropachev.restaurant_voting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNew;

public class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<Vote> getAll() {
        log.info("get all votes");
        return service.getAll();
    }

    public Vote get(int id) {
        log.info("get vote {}", id);
        return service.get(id);
    }

    public Vote create(Vote vote) {
        log.info("create vote {}", vote);
        //checkNew(vote);
        return service.create(vote);
    }

    public Vote create(int userId, int restaurantId) {
        //log.info("create vote {}", vote);
        //checkNew(vote);
        return service.create(userId, restaurantId);
    }

    public void delete(int id) {
        log.info("delete vote {}", id);
        service.delete(id);
    }

    public void update(Vote vote, int id) {
        log.info("update vote {} with id={}", vote, id);
        assureIdConsistent(vote, id);
        service.update(vote);
    }
}
