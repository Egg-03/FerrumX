package com.ferrumx.system.display.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Monitor {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Name")
    private String name;

    @SerializedName("PNPDeviceID")
    private String pnpDeviceId;

    @SerializedName("Status")
    private String status;

    @SerializedName("MonitorManufacturer")
    private String monitorManufacturer;

    @SerializedName("MonitorType")
    private String monitorType;

    @SerializedName("PixelsPerXLogicalInch")
    private int pixelsPerXLogicalInch;

    @SerializedName("PixelsPerYLogicalInch")
    private int pixelsPerYLogicalInch;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

