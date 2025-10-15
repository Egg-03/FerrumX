package org.ferrumx.core.mapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for mapping JSON strings to Java objects.
 * <p>
 * Provides methods to convert JSON responses (typically from PowerShell commands)
 * into either a {@link List} of objects or a single {@link Optional} object.
 * This class uses Gson for JSON deserialization.
 * </p>
 *
 * <b>Intended usage:</b> This utility is primarily designed to support the internal
 * service classes in {@code org.ferrumx.core.service} for mapping PowerShell JSON output
 * to domain entities. Consumers of the library should not need to invoke these methods directly,
 * unless they are parsing a custom POJO not already handled by the existing service classes.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * List<Battery> batteries = MapperUtil.mapToList(jsonString, Battery.class);
 * Optional<Processor> processor = MapperUtil.mapToObject(jsonString, Processor.class);
 * }</pre>
 * @since 2.0.0
 * @author Egg-03
 */

public class MapperUtil {

    private static final Gson gson = new Gson();

    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts a JSON string into a list of objects of the specified type.
     * <p>
     * If the JSON represents a single object, it is returned as a singleton list.
     * If the JSON is null or empty, returns an empty list.
     * </p>
     *
     * @param json        the JSON string to parse; must not be null
     * @param objectClass the class of the objects in the list; must not be null
     * @param <S>         the type of the object
     * @return a non-null list of objects deserialized from JSON
     * @throws com.google.gson.JsonSyntaxException if the JSON is malformed
     */
    @NotNull
    public static <S> List<S> mapToList(@NonNull String json, @NonNull Class<S> objectClass) {

        if(json.startsWith("[")) {
            Type listType = TypeToken.getParameterized(List.class, objectClass).getType();
            List<S> result = gson.fromJson(json, listType);
            return result!=null ? result : List.of();
        } else {
            S singleObject = gson.fromJson(json, objectClass);
            return singleObject!=null ? List.of(singleObject) : List.of();
        }
    }

    /**
     * Converts a JSON string into an {@link Optional} object of the specified type.
     * <p>
     * Returns {@link Optional#empty()} if the JSON is null or cannot be parsed into an object.
     * </p>
     *
     * @param json        the JSON string to parse; must not be null
     * @param objectClass the class of the object; must not be null
     * @param <S>         the type of the object
     * @return an {@link Optional} containing the deserialized object, or empty if null
     * @throws com.google.gson.JsonSyntaxException if the JSON is malformed
     */
    @NotNull
    public static <S> Optional<S> mapToObject(@NonNull String json, @NonNull Class<S> objectClass) {
        S object = gson.fromJson(json, objectClass);
        return Optional.ofNullable(object);
    }
}
