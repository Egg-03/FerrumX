package org.ferrumx.core.service.user;

import org.ferrumx.core.entity.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * Service class for fetching the current system user information.
 * <p>
 * This class retrieves the current user's details such as username,
 * home directory, and working directory using standard Java system properties.
 * <p>
 * Instances of this service are stateless and thread-safe; multiple threads
 * can safely invoke {@link #getUser()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * UserService userService = new UserService();
 * User currentUser = userService.getUser();
 * System.out.println(currentUser);
 * }</pre>
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

        return new User(
                System.getProperty("user.name"),
                System.getProperty("user.home"),
                System.getProperty("user.dir")
        );
    }
}
