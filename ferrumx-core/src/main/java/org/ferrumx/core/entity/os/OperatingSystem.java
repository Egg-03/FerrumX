package org.ferrumx.core.entity.os;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of the Windows Operating System.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_OperatingSystem} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * This class is annotated with Lombok {@link lombok.Value} to enforce immutability
 * <p>
 * Lombok {@link lombok.With} generates {@code withXxx(...)} methods
 * that allow safe copy-on-write modifications without breaking immutability.
 * <p>
 * JSON serialization and deserialization are handled by Gson.
 * Each field is annotated with {@link com.google.gson.annotations.SerializedName}
 * to ensure correct mapping from WMI JSON output.
 *
 * <h2>Thread safety</h2>
 * Instances are inherently thread-safe and may be safely cached or shared
 * across threads without external synchronization.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * OperatingSystem os = gson.fromJson(json, OperatingSystem.class);
 * OperatingSystem updated = os.withNumberOfUsers(5);
 * System.out.println(os); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-operatingsystem">Win32_OperatingSystem</a>
 */

@Value
@With
public class OperatingSystem {

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("InstallDate")
    @Nullable
    String installDate;

    @SerializedName("CSName")
    @Nullable
    String csName;

    @SerializedName("LastBootUpTime")
    @Nullable
    String lastBootUpTime;

    @SerializedName("LocalDateTime")
    @Nullable
    String localDateTime;

    @SerializedName("Distributed")
    @Nullable
    Boolean distributed;

    @SerializedName("NumberOfUsers")
    @Nullable
    Integer numberOfUsers;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("BootDevice")
    @Nullable
    String bootDevice;

    @SerializedName("BuildNumber")
    @Nullable
    String buildNumber;

    @SerializedName("BuildType")
    @Nullable
    String buildType;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("OSArchitecture")
    @Nullable
    String osArchitecture;

    @SerializedName("MUILanguages")
    @Nullable
    List<String> muiLanguages;

    @SerializedName("PortableOperatingSystem")
    @Nullable
    Boolean portableOperatingSystem;

    @SerializedName("Primary")
    @Nullable
    Boolean primary;

    @SerializedName("RegisteredUser")
    @Nullable
    String registeredUser;

    @SerializedName("SerialNumber")
    @Nullable
    String serialNumber;

    @SerializedName("ServicePackMajorVersion")
    @Nullable
    Integer servicePackMajorVersion;

    @SerializedName("ServicePackMinorVersion")
    @Nullable
    Integer servicePackMinorVersion;

    @SerializedName("SystemDirectory")
    @Nullable
    String systemDirectory;

    @SerializedName("SystemDrive")
    @Nullable
    String systemDrive;

    @SerializedName("WindowsDirectory")
    @Nullable
    String windowsDirectory;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}