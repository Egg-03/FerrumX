package org.ferrumx.core.service.user;

import org.ferrumx.core.entity.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * Service class for fetching the current system user information.
 * <p>
 * This class retrieves the current user's details such as username,
 * home directory, and working directory using standard Java system properties.
 */
public class UserService {

    /**
     * Retrieves the current system user information.
     *
     * @return a non-null {@link User} object containing the username,
     *         user home directory, and current working directory.
     */
    @NotNull
    public User getUser() {

        User user = new User();
        user.setUserName(System.getProperty("user.name"));
        user.setUserHome(System.getProperty("user.home"));
        user.setUserDirectory(System.getProperty("user.dir"));

        return user;
    }
}
