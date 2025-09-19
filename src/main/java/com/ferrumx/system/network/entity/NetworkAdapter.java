package com.ferrumx.system.network.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

@Data
public class NetworkAdapter {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Index")
    @Nullable
    private Integer index;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Description")
    @Nullable
    private String description;

    @SerializedName("PNPDeviceID")
    @Nullable
    private String pnpDeviceId;

    @SerializedName("MACAddress")
    @Nullable
    private String macAddress;

    @SerializedName("Installed")
    @Nullable
    private Boolean installed;

    @SerializedName("NetEnabled")
    @Nullable
    private Boolean netEnabled;

    @SerializedName("NetConnectionID")
    @Nullable
    private String netConnectionId;

    @SerializedName("PhysicalAdapter")
    @Nullable
    private Boolean physicalAdapter;

    @SerializedName("TimeOfLastReset")
    @Nullable
    private String timeOfLastReset;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

