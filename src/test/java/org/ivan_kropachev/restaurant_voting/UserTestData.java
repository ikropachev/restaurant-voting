package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Role;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int USER_ID = 100005;
    public static final int ADMIN_ID = 100004;
    public static final int NOT_FOUND = 100000;

    public static final User admin = new User(ADMIN_ID, "admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User user1 = new User(USER_ID, "user", "user@gmail.com", "user", Role.USER);
    public static final User user2 = new User(USER_ID + 1, "second_user", "second@gmail.com", "second_pass", Role.USER);
    public static final User user3 = new User(USER_ID + 2, "third_user", "third@gmail.com", "third_pass", Role.USER);

    //Users must be sorted by name, and e-mail for name duplicates.
    public static final List<User> users = List.of(admin, user2, user3, user1);

    public static User getNew() {
        return new User(null, "New_User", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setEmail("updated@gmail.com");
        updated.setName("Updated_Name");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
