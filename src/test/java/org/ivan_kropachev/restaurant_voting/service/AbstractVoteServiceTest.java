package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;

import static org.ivan_kropachev.restaurant_voting.RestaurantTestData.RESTAURANT1_ID;
import static org.ivan_kropachev.restaurant_voting.UserTestData.USER_ID;
import static org.ivan_kropachev.restaurant_voting.VoteTestData.*;
import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.NOT_FOUND;
import static org.ivan_kropachev.restaurant_voting.util.CheckTimeUtil.END_OF_CHANGE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractVoteServiceTest extends AbstractServiceTest {
    private final static LocalTime BEFORE_END_OF_CHANGE = END_OF_CHANGE.minus(Duration.ofMinutes(1));
    private final static LocalTime AFTER_END_OF_CHANGE = END_OF_CHANGE.plus(Duration.ofMinutes(1));

    @Autowired
    protected VoteService service;

    @Test
    void delete() {
        service.delete(VOTE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

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
