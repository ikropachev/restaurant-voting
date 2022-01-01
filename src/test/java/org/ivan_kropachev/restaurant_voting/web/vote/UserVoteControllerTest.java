package org.ivan_kropachev.restaurant_voting.web.vote;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.service.VoteService;
import org.ivan_kropachev.restaurant_voting.web.AbstractControllerTest;
import org.ivan_kropachev.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ivan_kropachev.restaurant_voting.Constants.RESTAURANT1_ID;
import static org.ivan_kropachev.restaurant_voting.TestUtil.userHttpBasic;
import static org.ivan_kropachev.restaurant_voting.UserTestData.user1;
import static org.ivan_kropachev.restaurant_voting.VoteTestData.VOTE_MATCHER;
import static org.ivan_kropachev.restaurant_voting.VoteTestData.getNew;

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
