package org.ferrumx.core.entity.battery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class Battery {

    @SerializedName("DeviceID")
    @Nullable
    private String deviceId;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("Description")
    @Nullable
    private String description;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Status")
    @Nullable
    private String status;

    @SerializedName("PowerManagementCapabilities")
    @Nullable
    private List<String> powerManagementCapabilities;

    @SerializedName("PowerManagementSupported")
    @Nullable
    private Boolean powerManagementSupported;

    @SerializedName("BatteryStatus")
    @Nullable
    private Integer batteryStatus;

    @SerializedName("Chemistry")
    @Nullable
    private Integer chemistry;

    @SerializedName("DesignCapacity")
    @Nullable
    private Integer designCapacity;

    @SerializedName("DesignVoltage")
    @Nullable
    private Integer designVoltage;

    @SerializedName("EstimatedChargeRemaining")
    @Nullable
    private Integer estimatedChargeRemaining;

    @SerializedName("EstimatedRunTime")
    @Nullable
    private Integer estimatedRunTime;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}