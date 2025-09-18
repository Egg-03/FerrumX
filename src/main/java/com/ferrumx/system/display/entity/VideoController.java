package com.ferrumx.system.display.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class VideoController {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Name")
    private String name;

    @SerializedName("PNPDeviceID")
    private String pnpDeviceId;

    @SerializedName("CurrentBitsPerPixel")
    private int currentBitsPerPixel;

    @SerializedName("CurrentHorizontalResolution")
    private int currentHorizontalResolution;

    @SerializedName("CurrentVerticalResolution")
    private int currentVerticalResolution;

    @SerializedName("CurrentRefreshRate")
    private int currentRefreshRate;

    @SerializedName("MaxRefreshRate")
    private int maxRefreshRate;

    @SerializedName("MinRefreshRate")
    private int minRefreshRate;

    @SerializedName("AdapterDACType")
    private String adapterDacType;

    @SerializedName("AdapterRAM")
    private long adapterRam;

    @SerializedName("DriverDate")
    private String driverDate;

    @SerializedName("DriverVersion")
    private String driverVersion;

    @SerializedName("VideoProcessor")
    private String videoProcessor;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

