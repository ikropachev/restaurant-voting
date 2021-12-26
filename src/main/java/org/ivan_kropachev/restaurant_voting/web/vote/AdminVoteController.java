package org.ivan_kropachev.restaurant_voting.web.vote;

import io.swagger.annotations.*;
import org.ivan_kropachev.restaurant_voting.AuthorizedUser;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalTime;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.web.SecurityUtil.authUserId;


@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description="Operations for votes from admin")
public class AdminVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping
    @ApiOperation(value = "View a list of all votes")
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "View a vote by id")
    public Vote get(@PathVariable @ApiParam(example = "100027", required = true) int id) {
        return super.get(id);
    }

    //@ApiOperation(value = "111", authorizations = {@Authorization(value = "basicAuth")})
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "Authorization", value = "Authentication Token", required = true, dataType = "String", paramType = "header")
    //})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a vote for a restaurant")
    public Vote createWithLocation(@RequestParam (value = "restaurant-id") @ApiParam(example = "100002", required = true)
                                               int restaurantId) {
        log.info("create/update vote from user with id {}", authUserId());
        if (LocalTime.now().isAfter(LocalTime.of(23, 59))) {
            throw new LateVoteException("Too late for voting");
        }
        return super.save(authUserId(), restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a vote by id")
    public void delete(@PathVariable @ApiParam(example = "100023", required = true) int id) {
        super.delete(id);
    }
}
