package org.ikropachev.voting.web.vote;

import org.ikropachev.voting.model.Vote;
import org.ikropachev.voting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

public class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    //For tests from API
    public List<Vote> getAll() {
        log.info("get all votes");
        return service.getAll();
    }

    public Vote save(int userId, int restaurantId) {
        return service.save(userId, restaurantId, LocalTime.now());
    }
}
