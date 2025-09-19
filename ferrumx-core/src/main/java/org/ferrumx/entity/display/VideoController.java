package org.ferrumx.entity.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class VideoController {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("PNPDeviceID")
    @Nullable
    private String pnpDeviceId;

    @SerializedName("CurrentBitsPerPixel")
    @Nullable
    private Integer currentBitsPerPixel;

    @SerializedName("CurrentHorizontalResolution")
    @Nullable
    private Integer currentHorizontalResolution;

    @SerializedName("CurrentVerticalResolution")
    @Nullable
    private Integer currentVerticalResolution;

    @SerializedName("CurrentRefreshRate")
    @Nullable
    private Integer currentRefreshRate;

    @SerializedName("MaxRefreshRate")
    @Nullable
    private Integer maxRefreshRate;

    @SerializedName("MinRefreshRate")
    @Nullable
    private Integer minRefreshRate;

    @SerializedName("AdapterDACType")
    @Nullable
    private String adapterDacType;

    @SerializedName("AdapterRAM")
    @Nullable
    private Long adapterRam;

    @SerializedName("DriverDate")
    @Nullable
    private String driverDate;

    @SerializedName("DriverVersion")
    @Nullable
    private String driverVersion;

    @SerializedName("VideoProcessor")
    @Nullable
    private String videoProcessor;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}