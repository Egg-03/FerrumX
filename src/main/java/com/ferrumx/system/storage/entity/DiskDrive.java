package com.ferrumx.system.storage.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DiskDrive {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Model")
    private String model;

    @SerializedName("Size")
    private long size;

    @SerializedName("FirmwareRevision")
    private String firmwareRevision;

    @SerializedName("SerialNumber")
    private String serialNumber;

    @SerializedName("Partitions")
    private int partitions;

    @SerializedName("Status")
    private String status;

    @SerializedName("InterfaceType")
    private String interfaceType;

    @SerializedName("PNPDeviceID")
    private String pnpDeviceId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
