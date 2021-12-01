package org.ivan_kropachev.restaurant_voting.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private String privileged; //have or not admin privilegies

    public User() {
    }

    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.privileged);
    }

    public User(Integer id, String name, String email, String password, String privileged) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.privileged = privileged;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivileged() {
        return privileged;
    }

    public void setPrivileged(String privileged) {
        this.privileged = privileged;
    }
}
