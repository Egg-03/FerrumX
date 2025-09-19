package org.ferrumx.service.user;

import org.ferrumx.entity.user.User;
import org.jetbrains.annotations.NotNull;

public class UserService {

    @NotNull
    public User getUser() {

        User user = new User();
        user.setUserName(System.getProperty("user.name"));
        user.setUserHome(System.getProperty("user.home"));
        user.setUserDirectory(System.getProperty("user.dir"));

        return user;
    }
}
