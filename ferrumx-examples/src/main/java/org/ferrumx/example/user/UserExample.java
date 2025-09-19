package org.ferrumx.example.user;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.user.User;
import org.ferrumx.core.service.user.UserService;

@Slf4j
public class UserExample {

    User user = new UserService().getUser();

    public static void main(String[] args) {
        User user = new UserService().getUser();

        log.info("User: \n{}", user);

        // individual fields are accessible via getter methods
        log.info("User Name: {}", user.getUserName());
        log.info("User Home: {}", user.getUserHome());
        log.info("User Directory: {}", user.getUserDirectory());
    }
}
