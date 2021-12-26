package org.ivan_kropachev.restaurant_voting.web.vote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static org.ivan_kropachev.restaurant_voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description="Operations for votes from regular user")
public class UserVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/user/votes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a vote for a restaurant")
    public Vote createWithLocation(@RequestParam(value = "restaurant-id") int restaurantId) {
        log.info("create/update vote from user with id {}", authUserId());
        if (LocalTime.now().isAfter(LocalTime.of(23, 59))) {
            throw new LateVoteException("Too late for voting");
        }
        return super.save(authUserId(), restaurantId);
    }
}
