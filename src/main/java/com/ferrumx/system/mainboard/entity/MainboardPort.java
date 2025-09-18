package com.ferrumx.system.mainboard.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MainboardPort {
    @SerializedName("Tag")
    private String tag;

    @SerializedName("ExternalReferenceDesignator")
    private String externalReferenceDesignator;

    @SerializedName("InternalReferenceDesignator")
    private String internalReferenceDesignator;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
