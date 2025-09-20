package org.ferrumx.core.entity.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Logical Disk Partition on a Windows system.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_DiskPartition WMI class.
 */
@Data
public class DiskPartition {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Description")
    @Nullable
    private String description;

    @SerializedName("BlockSize")
    @Nullable
    private Long blockSize;

    @SerializedName("NumberOfBlocks")
    @Nullable
    private Long numberOfBlocks;

    @SerializedName("Bootable")
    @Nullable
    private Boolean bootable;

    @SerializedName("PrimaryPartition")
    @Nullable
    private Boolean primaryPartition;

    @SerializedName("BootPartition")
    @Nullable
    private Boolean bootPartition;

    @SerializedName("DiskIndex")
    @Nullable
    private Integer diskIndex;

    @SerializedName("Size")
    @Nullable
    private Long size;

    @SerializedName("Type")
    @Nullable
    private String type;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}