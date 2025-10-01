package org.ferrumx.core.entity.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a physical or logical drive on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_DiskDrive} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * This class is annotated with Lombok {@link lombok.Value} to enforce immutability.
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
 * DiskDrive drive = gson.fromJson(json, DiskDrive.class);
 * System.out.println(drive); // Pretty-printed JSON
 * }</pre>
 *
 * {@link DiskPartition} for information about partitions on the disk.
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-diskdrive">Win32_DiskDrive</a>
 */

@Value
@Builder(toBuilder = true)
public class DiskDrive {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("Model")
    @Nullable
    String model;

    @SerializedName("Size")
    @Nullable
    Long size;

    @SerializedName("FirmwareRevision")
    @Nullable
    String firmwareRevision;

    @SerializedName("SerialNumber")
    @Nullable
    String serialNumber;

    @SerializedName("Partitions")
    @Nullable
    Integer partitions;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("InterfaceType")
    @Nullable
    String interfaceType;

    @SerializedName("PNPDeviceID")
    @Nullable
    String pnpDeviceId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}