package com.ferrumx.system.network.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import com.google.gson.annotations.SerializedName;

@Data
public class NetworkAdapter {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Index")
    private int index;

    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String description;

    @SerializedName("PNPDeviceID")
    private String pnpDeviceId;

    @SerializedName("MACAddress")
    private String macAddress;

    @SerializedName("Installed")
    private boolean installed;

    @SerializedName("NetEnabled")
    private boolean netEnabled;

    @SerializedName("NetConnectionID")
    private String netConnectionId;

    @SerializedName("PhysicalAdapter")
    private boolean physicalAdapter;

    @SerializedName("TimeOfLastReset")
    private String timeOfLastReset;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}


