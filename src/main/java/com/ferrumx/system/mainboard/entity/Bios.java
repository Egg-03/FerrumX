package com.ferrumx.system.mainboard.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Bios {
    @SerializedName("Name")
    private String name;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("ReleaseDate")
    private String releaseDate;

    @SerializedName("SMBIOSPResent")
    private boolean smbiosPresent;

    @SerializedName("Status")
    private String status;

    @SerializedName("Version")
    private String version;

    @SerializedName("CurrentLanguage")
    private String currentLanguage;

    @SerializedName("SMBIOSBIOSVersion")
    private String smbiosBiosVersion;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

