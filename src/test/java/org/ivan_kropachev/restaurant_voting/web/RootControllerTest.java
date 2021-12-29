package org.ivan_kropachev.restaurant_voting.web;

import org.junit.jupiter.api.Test;

import static org.ivan_kropachev.restaurant_voting.TestUtil.userAuth;
import static org.ivan_kropachev.restaurant_voting.UserTestData.user1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    void login() throws Exception {
        perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
    }

    @Test
    void index() throws Exception {
        perform(get("/index")
                .with(userAuth(user1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }

    @Test
    void unAuth() throws Exception {
        perform(get("/index"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void root() throws Exception {
        perform(get("/")
                .with(userAuth(user1)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:index"));
    }

    @Test
    void rootUnAuth() throws Exception {
        perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
