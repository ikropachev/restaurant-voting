package org.ivan_kropachev.restaurant_voting.web.vote;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote createWithLocation(@PathVariable int restaurantId) {
        log.info("create/update vote from user with id {}", authUserId());
        if (LocalTime.now().isAfter(LocalTime.of(23, 00))) {
            throw new LateVoteException("Too late for voting");
        }
        return super.save(authUserId(), restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
