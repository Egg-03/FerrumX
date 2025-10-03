package org.ferrumx.core.entity.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a physical or logical drive on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_DiskDrive} WMI class.
 * Values are captured at query time and do not automatically update.
 * <p>
 * Instances are thread-safe and may be safely cached or shared across threads.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Build a new DiskDrive instance
 * DiskDrive drive = DiskDrive.builder()
 *     .deviceId("\\\\.\\PHYSICALDRIVE0")
 *     .caption("Samsung SSD 970 EVO")
 *     .model("MZ-V7E1T0")
 *     .size(1000204886016L)
 *     .firmwareRevision("2B2QEXM7")
 *     .serialNumber("S4EVNX0M123456")
 *     .partitions(3)
 *     .status("OK")
 *     .interfaceType("NVMe")
 *     .pnpDeviceId("PCI\\VEN_144D&DEV_A808&SUBSYS_0A0E144D&REV_01\\4&1A2B3C4D&0&000000")
 *     .build();
 *
 * // Create a modified copy
 * DiskDrive updatedDrive = drive.toBuilder()
 *     .size(2000409772032L)
 *     .build();
 *
 * }</pre>
 *
 * {@link DiskPartition} contains information about partitions on this disk.
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