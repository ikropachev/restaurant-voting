package org.ivan_kropachev.restaurant_voting.web.vote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.web.SecurityUtil.authUserId;
import static org.ivan_kropachev.restaurant_voting.web.restaurant.AdminRestaurantController.RESTAURANT1_ID_STR;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for votes from admin")
public class AdminVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping
    @ApiOperation(value = "View a list of all votes")
    public List<Vote> getAll() {
        return super.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create/update a vote for a restaurant")
    public Vote createWithLocation(@RequestParam(value = "restaurant-id") @ApiParam(example = RESTAURANT1_ID_STR, required = true)
                                           int restaurantId) {
        log.info("create/update vote from user with id {}", authUserId());
        return super.save(authUserId(), restaurantId);
    }
}
