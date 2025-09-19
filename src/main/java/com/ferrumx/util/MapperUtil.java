package com.ferrumx.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class MapperUtil {

    private static final Gson gson = new Gson();

    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

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

    @NotNull
    public static <S> Optional<S> mapToObject(@NonNull String json, @NonNull Class<S> objectClass) {
        S object = gson.fromJson(json, objectClass);
        return Optional.ofNullable(object);
    }
}
