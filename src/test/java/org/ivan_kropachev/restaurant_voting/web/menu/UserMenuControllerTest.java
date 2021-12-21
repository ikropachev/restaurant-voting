package org.ivan_kropachev.restaurant_voting.web.menu;

import org.ivan_kropachev.restaurant_voting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ivan_kropachev.restaurant_voting.MenuTestData.*;
import static org.ivan_kropachev.restaurant_voting.TestUtil.userHttpBasic;
import static org.ivan_kropachev.restaurant_voting.UserTestData.admin;
import static org.ivan_kropachev.restaurant_voting.UserTestData.user1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserMenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserMenuController.REST_URL;

    @Test
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu5, menu6));
    }
}
