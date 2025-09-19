package com.ferrumx.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class ProcessorCache {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Purpose")
    @Nullable
    private String purpose;

    @SerializedName("InstalledSize")
    @Nullable
    private Integer installedSize;

    @SerializedName("Associativity")
    @Nullable
    private Integer associativity;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}