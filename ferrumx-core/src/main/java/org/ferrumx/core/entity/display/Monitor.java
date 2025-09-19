package org.ferrumx.core.entity.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class Monitor {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("PNPDeviceID")
    @Nullable
    private String pnpDeviceId;

    @SerializedName("Status")
    @Nullable
    private String status;

    @SerializedName("MonitorManufacturer")
    @Nullable
    private String monitorManufacturer;

    @SerializedName("MonitorType")
    @Nullable
    private String monitorType;

    @SerializedName("PixelsPerXLogicalInch")
    @Nullable
    private Integer pixelsPerXLogicalInch;

    @SerializedName("PixelsPerYLogicalInch")
    @Nullable
    private Integer pixelsPerYLogicalInch;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}