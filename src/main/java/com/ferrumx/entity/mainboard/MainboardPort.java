package com.ferrumx.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class MainboardPort {

    @SerializedName("Tag")
    @Nullable
    private String tag;

    @SerializedName("ExternalReferenceDesignator")
    @Nullable
    private String externalReferenceDesignator;

    @SerializedName("InternalReferenceDesignator")
    @Nullable
    private String internalReferenceDesignator;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}