package com.ferrumx.system.mainboard.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Mainboard {
    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("Model")
    private String model;

    @SerializedName("Product")
    private String product;

    @SerializedName("SerialNumber")
    private String serialNumber;

    @SerializedName("Version")
    private String version;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

