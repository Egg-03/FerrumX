package com.ferrumx.system.memory.entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PhysicalMemory {

    @SerializedName("Tag")
    private String tag;

    @SerializedName("Name")
    private String name;

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("Model")
    private String model;

    @SerializedName("OtherIdentifyingInfo")
    private String otherIdentifyingInfo;

    @SerializedName("PartNumber")
    private String partNumber;

    @SerializedName("FormFactor")
    private int formFactor;

    @SerializedName("BankLabel")
    private String bankLabel;

    @SerializedName("Capacity")
    private long capacity;

    @SerializedName("DataWidth")
    private int dataWidth;

    @SerializedName("Speed")
    private int speed;

    @SerializedName("ConfiguredClockSpeed")
    private int configuredClockSpeed;

    @SerializedName("DeviceLocator")
    private String deviceLocator;

    @SerializedName("SerialNumber")
    private String serialNumber;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

