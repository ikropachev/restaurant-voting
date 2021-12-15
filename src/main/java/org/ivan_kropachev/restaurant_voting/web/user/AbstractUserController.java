package org.ivan_kropachev.restaurant_voting.web.user;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.service.UserService;
import org.ivan_kropachev.restaurant_voting.to.UserTo;
import org.ivan_kropachev.restaurant_voting.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static org.ivan_kropachev.restaurant_voting.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get user {}", id);
        return service.get(id);
    }

    public void create(UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        service.create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        log.info("create user {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete user {}", id);
        service.delete(id);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public void update(User user, int id) {
        log.info("update user {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}
