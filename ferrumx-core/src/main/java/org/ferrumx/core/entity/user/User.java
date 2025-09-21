package org.ferrumx.core.entity.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a user on a Windows system.
 * <p>
 * Fields capture basic user information such as username, user home and user directory.
 * <p>
 * This class is annotated with Lombok {@link lombok.Value} to enforce immutability
 * <p>
 * Lombok {@link lombok.With} generates {@code withXxx(...)} methods
 * that allow safe copy-on-write modifications without breaking immutability.
 * <p>
 * JSON serialization and deserialization are handled by Gson.
 *
 * <h2>Thread safety</h2>
 * Instances are inherently thread-safe and may be safely cached or shared
 * across threads without external synchronization.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * User user = gson.fromJson(json, User.class);
 * User updated = user.withUserName("newUserName");
 * System.out.println(user); // Pretty-printed JSON
 * }</pre>
 */

@Value
@With
public class User {

    @Nullable
    String userName;

    @Nullable
    String userHome;

    @Nullable
    String userDirectory;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
