package org.ivan_kropachev.restaurant_voting.web.user;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.springframework.stereotype.Controller;

import static org.ivan_kropachev.restaurant_voting.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
