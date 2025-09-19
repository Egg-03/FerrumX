package com.ferrumx.system.storage.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class DiskDrive {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("Model")
    @Nullable
    private String model;

    @SerializedName("Size")
    @Nullable
    private Long size;

    @SerializedName("FirmwareRevision")
    @Nullable
    private String firmwareRevision;

    @SerializedName("SerialNumber")
    @Nullable
    private String serialNumber;

    @SerializedName("Partitions")
    @Nullable
    private Integer partitions;

    @SerializedName("Status")
    @Nullable
    private String status;

    @SerializedName("InterfaceType")
    @Nullable
    private String interfaceType;

    @SerializedName("PNPDeviceID")
    @Nullable
    private String pnpDeviceId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}