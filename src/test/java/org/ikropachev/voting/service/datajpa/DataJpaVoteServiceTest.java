package org.ikropachev.voting.service.datajpa;

import org.ikropachev.voting.model.Vote;
import org.ikropachev.voting.service.AbstractServiceTest;
import org.ikropachev.voting.service.VoteService;
import org.ikropachev.voting.util.exception.LateVoteException;
import org.ikropachev.voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;

import static org.ikropachev.voting.RestaurantTestData.RESTAURANT1_ID;
import static org.ikropachev.voting.UserTestData.USER_ID;
import static org.ikropachev.voting.VoteTestData.*;
import static org.ikropachev.voting.model.AbstractBaseEntity.NOT_FOUND;
import static org.ikropachev.voting.util.CheckTimeUtil.END_OF_CHANGE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataJpaVoteServiceTest extends AbstractServiceTest {
    private final static LocalTime BEFORE_END_OF_CHANGE = END_OF_CHANGE.minus(Duration.ofMinutes(1));
    private final static LocalTime AFTER_END_OF_CHANGE = END_OF_CHANGE.plus(Duration.ofMinutes(1));

    @Autowired
    protected VoteService service;

    @Test
    void saveNewBeforeDeadLine() {
        Vote created = service.save(USER_ID, RESTAURANT1_ID, BEFORE_END_OF_CHANGE);
        int newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    void saveNewAfterDeadLine() {
        Vote created = service.save(USER_ID, RESTAURANT1_ID, AFTER_END_OF_CHANGE);
        int newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    void updateBeforeDeadLine() {
        service.save(USER_ID, RESTAURANT1_ID, BEFORE_END_OF_CHANGE);
        Vote updated = service.save(USER_ID, RESTAURANT1_ID, BEFORE_END_OF_CHANGE);
        int newId = updated.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(updated, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    void updateAfterDeadLine() {
        service.save(USER_ID, RESTAURANT1_ID, BEFORE_END_OF_CHANGE);
        assertThrows(LateVoteException.class, () -> service.save(USER_ID, RESTAURANT1_ID, AFTER_END_OF_CHANGE));
    }

    @Test
    void get() {
        Vote actual = service.get(VOTE1_ID);
        VOTE_MATCHER.assertMatch(actual, vote1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getAll() {
        VOTE_MATCHER.assertMatch(service.getAll(), votes);
    }
}
