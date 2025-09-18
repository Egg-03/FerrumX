package com.ferrumx.system.storage.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DiskPartition {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String description;

    @SerializedName("BlockSize")
    private Long blockSize;

    @SerializedName("NumberOfBlocks")
    private Long numberOfBlocks;

    @SerializedName("Bootable")
    private boolean bootable;

    @SerializedName("PrimaryPartition")
    private boolean primaryPartition;

    @SerializedName("BootPartition")
    private boolean bootPartition;

    @SerializedName("DiskIndex")
    private int diskIndex;

    @SerializedName("Size")
    private Long size;

    @SerializedName("Type")
    private String type;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

