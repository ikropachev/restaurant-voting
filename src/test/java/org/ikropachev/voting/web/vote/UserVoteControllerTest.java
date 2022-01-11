package org.ikropachev.voting.web.vote;

import org.ikropachev.voting.model.Vote;
import org.ikropachev.voting.service.VoteService;
import org.ikropachev.voting.web.AbstractControllerTest;
import org.ikropachev.voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ikropachev.voting.RestaurantTestData.RESTAURANT1_ID;
import static org.ikropachev.voting.TestUtil.userHttpBasic;
import static org.ikropachev.voting.UserTestData.user1;
import static org.ikropachev.voting.VoteTestData.VOTE_MATCHER;
import static org.ikropachev.voting.VoteTestData.getNew;

//Check time in CheckTimeUtil before test
public class UserVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserVoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "?restaurant-id=" + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user1))
                .content(JsonUtil.writeValue(newVote)));

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }
}
