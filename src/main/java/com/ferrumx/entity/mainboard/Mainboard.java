package com.ferrumx.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class Mainboard {

    @SerializedName("Manufacturer")
    @Nullable
    private String manufacturer;

    @SerializedName("Model")
    @Nullable
    private String model;

    @SerializedName("Product")
    @Nullable
    private String product;

    @SerializedName("SerialNumber")
    @Nullable
    private String serialNumber;

    @SerializedName("Version")
    @Nullable
    private String version;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}