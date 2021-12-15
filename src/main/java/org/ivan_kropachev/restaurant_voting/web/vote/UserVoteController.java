package org.ivan_kropachev.restaurant_voting.web.vote;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/user/votes";

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote createWithLocation(@PathVariable int restaurantId) {
        log.info("before new Vote");
        int userId = 100005;
        if (LocalTime.now().isAfter(LocalTime.of(23, 00))) {
            throw new LateVoteException("Too late for voting");
        }
        log.info("after new Vote");
        return super.create(userId, restaurantId);
    }
}