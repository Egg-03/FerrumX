package com.ferrumx.system.os.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

@Data
public class OperatingSystem {

    @SerializedName("Name")
    private String name;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("InstallDate")
    private String installDate;

    @SerializedName("CSName")
    private String csName;

    @SerializedName("LastBootUpTime")
    private String lastBootUpTime;

    @SerializedName("LocalDateTime")
    private String localDateTime;

    @SerializedName("Distributed")
    private boolean distributed;

    @SerializedName("NumberOfUsers")
    private int numberOfUsers;

    @SerializedName("Version")
    private String version;

    @SerializedName("BootDevice")
    private String bootDevice;

    @SerializedName("BuildNumber")
    private String buildNumber;

    @SerializedName("BuildType")
    private String buildType;

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("OSArchitecture")
    private String osArchitecture;

    @SerializedName("MUILanguages")
    private List<String> muiLanguages;

    @SerializedName("PortableOperatingSystem")
    private boolean portableOperatingSystem;

    @SerializedName("Primary")
    private boolean primary;

    @SerializedName("RegisteredUser")
    private String registeredUser;

    @SerializedName("SerialNumber")
    private String serialNumber;

    @SerializedName("ServicePackMajorVersion")
    private int servicePackMajorVersion;

    @SerializedName("ServicePackMinorVersion")
    private int servicePackMinorVersion;

    @SerializedName("SystemDirectory")
    private String systemDirectory;

    @SerializedName("SystemDrive")
    private String systemDrive;

    @SerializedName("WindowsDirectory")
    private String windowsDirectory;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}