package io.github.eggy03.ferrumx.example.user;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.user.User;
import io.github.eggy03.ferrumx.core.service.user.UserService;

/**
 * Example class demonstrating how to fetch and display the current system user information
 * using {@link UserService}.
 * <p>
 * This class retrieves a {@link User} object representing the current system user
 * and logs its JSON representation. Individual fields such as username, home directory,
 * and current working directory can be accessed via getter methods.
 * </p>
 */
@Slf4j
public class UserExample {

    public static void main(String[] args) {
        User user = new UserService().getUser();

        log.info("User: \n{}", user);

        // individual fields are accessible via getter methods
        log.info("User Name: {}", user.getUserName());
        log.info("User Home: {}", user.getUserHome());
        log.info("User Directory: {}", user.getUserDirectory());
    }
}
