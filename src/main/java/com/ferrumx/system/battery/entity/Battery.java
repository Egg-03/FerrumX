package com.ferrumx.system.battery.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Battery {

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Description")
    private String description;

    @SerializedName("Name")
    private String name;

    @SerializedName("Status")
    private String status;

    @SerializedName("PowerManagementCapabilities")
    private String[] powerManagementCapabilities;

    @SerializedName("PowerManagementSupported")
    private Boolean powerManagementSupported;

    @SerializedName("BatteryStatus")
    private Integer batteryStatus;

    @SerializedName("Chemistry")
    private Integer chemistry;

    @SerializedName("DesignCapacity")
    private Integer designCapacity;

    @SerializedName("DesignVoltage")
    private Integer designVoltage;

    @SerializedName("EstimatedChargeRemaining")
    private Integer estimatedChargeRemaining;

    @SerializedName("EstimatedRunTime")
    private Integer estimatedRunTime;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}