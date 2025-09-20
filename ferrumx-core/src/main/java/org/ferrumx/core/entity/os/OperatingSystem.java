package org.ferrumx.core.entity.os;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents the Windows Operating System.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_OperatingSystem WMI class.
 */
@Data
public class OperatingSystem {

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("InstallDate")
    @Nullable
    private String installDate;

    @SerializedName("CSName")
    @Nullable
    private String csName;

    @SerializedName("LastBootUpTime")
    @Nullable
    private String lastBootUpTime;

    @SerializedName("LocalDateTime")
    @Nullable
    private String localDateTime;

    @SerializedName("Distributed")
    @Nullable
    private Boolean distributed;

    @SerializedName("NumberOfUsers")
    @Nullable
    private Integer numberOfUsers;

    @SerializedName("Version")
    @Nullable
    private String version;

    @SerializedName("BootDevice")
    @Nullable
    private String bootDevice;

    @SerializedName("BuildNumber")
    @Nullable
    private String buildNumber;

    @SerializedName("BuildType")
    @Nullable
    private String buildType;

    @SerializedName("Manufacturer")
    @Nullable
    private String manufacturer;

    @SerializedName("OSArchitecture")
    @Nullable
    private String osArchitecture;

    @SerializedName("MUILanguages")
    @Nullable
    private List<String> muiLanguages;

    @SerializedName("PortableOperatingSystem")
    @Nullable
    private Boolean portableOperatingSystem;

    @SerializedName("Primary")
    @Nullable
    private Boolean primary;

    @SerializedName("RegisteredUser")
    @Nullable
    private String registeredUser;

    @SerializedName("SerialNumber")
    @Nullable
    private String serialNumber;

    @SerializedName("ServicePackMajorVersion")
    @Nullable
    private Integer servicePackMajorVersion;

    @SerializedName("ServicePackMinorVersion")
    @Nullable
    private Integer servicePackMinorVersion;

    @SerializedName("SystemDirectory")
    @Nullable
    private String systemDirectory;

    @SerializedName("SystemDrive")
    @Nullable
    private String systemDrive;

    @SerializedName("WindowsDirectory")
    @Nullable
    private String windowsDirectory;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}