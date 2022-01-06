package org.ivan_kropachev.restaurant_voting.service;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.ivan_kropachev.restaurant_voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.ivan_kropachev.restaurant_voting.Constants.*;
import static org.ivan_kropachev.restaurant_voting.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractVoteServiceTest extends AbstractServiceTest {
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
    void save() {
        Vote created = service.save(USER_ID, RESTAURANT1_ID);
        int newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    void updateAfterVoteTime() {
        service.save(USER_ID, RESTAURANT1_ID);
        assertThrows(LateVoteException.class, () -> service.save(USER_ID, RESTAURANT1_ID));
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
