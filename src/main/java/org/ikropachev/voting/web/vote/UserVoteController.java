package org.ikropachev.voting.web.vote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ikropachev.voting.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.ikropachev.voting.web.SecurityUtil.authUserId;
import static org.ikropachev.voting.web.restaurant.AdminRestaurantController.RESTAURANT1_ID_STR;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for votes from regular user")
public class UserVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/user/votes";

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create/update a vote for a restaurant")
    public Vote createWithLocation(@RequestParam(value = "restaurant-id") @ApiParam(example = RESTAURANT1_ID_STR, required = true)
                                           int restaurantId) {
        log.info("create/update vote from user with id {}", authUserId());
        return super.save(authUserId(), restaurantId);
    }
}
