package com.ferrumx.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;

import java.lang.reflect.Type;
import java.util.List;

public class MapperUtil {

    private static final Gson gson = new Gson();

    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <S> List<S> mapToList(@NonNull String json, @NonNull Class<S> objectClass) {

        if(json.startsWith("[")) {
            Type listType = TypeToken.getParameterized(List.class, objectClass).getType();
            return gson.fromJson(json, listType);
        } else {
            S singleObject = gson.fromJson(json, objectClass);
            return List.of(singleObject);
        }

    }

    public static <S> S mapToObject(@NonNull String json, @NonNull Class<S> objectClass) {
        return gson.fromJson(json, objectClass);
    }
}
