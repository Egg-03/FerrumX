package com.ferrumx.system.processor.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProcessorCache {
    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Purpose")
    private String purpose;

    @SerializedName("InstalledSize")
    private int installedSize;

    @SerializedName("Associativity")
    private int associativity;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
