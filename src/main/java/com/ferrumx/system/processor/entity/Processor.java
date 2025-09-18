package com.ferrumx.system.processor.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import com.google.gson.annotations.SerializedName;

@Data
public class Processor {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Name")
    private String name;

    @SerializedName("NumberOfCores")
    private int numberOfCores;

    @SerializedName("ThreadCount")
    private int threadCount;

    @SerializedName("NumberOfLogicalProcessors")
    private int numberOfLogicalProcessors;

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("AddressWidth")
    private int addressWidth;

    @SerializedName("L2CacheSize")
    private int l2CacheSize;

    @SerializedName("L3CacheSize")
    private int l3CacheSize;

    @SerializedName("MaxClockSpeed")
    private int maxClockSpeed;

    @SerializedName("ExtClock")
    private int extClock;

    @SerializedName("SocketDesignation")
    private String socketDesignation;

    @SerializedName("Version")
    private String version;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Family")
    private int family;

    @SerializedName("Stepping")
    private int stepping;

    @SerializedName("VirtualizationFirmwareEnabled")
    private boolean virtualizationFirmwareEnabled;

    @SerializedName("ProcessorId")
    private String processorId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}


