package org.ferrumx.core.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a CPU device on a Windows system.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_Processor WMI class.
 * <p>
 * See also: {@link ProcessorCache}
 */
@Data
public class Processor {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("NumberOfCores")
    @Nullable
    private Integer numberOfCores;

    @SerializedName("ThreadCount")
    @Nullable
    private Integer threadCount;

    @SerializedName("NumberOfLogicalProcessors")
    @Nullable
    private Integer numberOfLogicalProcessors;

    @SerializedName("Manufacturer")
    @Nullable
    private String manufacturer;

    @SerializedName("AddressWidth")
    @Nullable
    private Integer addressWidth;

    @SerializedName("L2CacheSize")
    @Nullable
    private Integer l2CacheSize;

    @SerializedName("L3CacheSize")
    @Nullable
    private Integer l3CacheSize;

    @SerializedName("MaxClockSpeed")
    @Nullable
    private Integer maxClockSpeed;

    @SerializedName("ExtClock")
    @Nullable
    private Integer extClock;

    @SerializedName("SocketDesignation")
    @Nullable
    private String socketDesignation;

    @SerializedName("Version")
    @Nullable
    private String version;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("Family")
    @Nullable
    private Integer family;

    @SerializedName("Stepping")
    @Nullable
    private Integer stepping;

    @SerializedName("VirtualizationFirmwareEnabled")
    @Nullable
    private Boolean virtualizationFirmwareEnabled;

    @SerializedName("ProcessorId")
    @Nullable
    private String processorId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}


