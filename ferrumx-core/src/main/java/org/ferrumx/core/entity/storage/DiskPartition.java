package org.ferrumx.core.entity.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a logical disk partition on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_DiskPartition} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * This class is annotated with Lombok {@link Value} to enforce immutability
 * <p>
 * Lombok {@link With} generates {@code withXxx(...)} methods
 * that allow safe copy-on-write modifications without breaking immutability.
 * <p>
 * JSON serialization and deserialization are handled by Gson.
 * Each field is annotated with {@link SerializedName}
 * to ensure correct mapping from WMI JSON output.
 *
 * <h2>Thread safety</h2>
 * Instances are inherently thread-safe and may be safely cached or shared
 * across threads without external synchronization.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DiskPartition partition = gson.fromJson(json, DiskPartition.class);
 * DiskPartition updated = partition.withBootPartition(true);
 * System.out.println(partition); // Pretty-printed JSON
 * }</pre>
 *
 * {@link DiskDrive} for additional disk information.
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-diskpartition">Win32_DiskPartition</a>
 */

@Value
@With
public class DiskPartition {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Description")
    @Nullable
    String description;

    @SerializedName("BlockSize")
    @Nullable
    Long blockSize;

    @SerializedName("NumberOfBlocks")
    @Nullable
    Long numberOfBlocks;

    @SerializedName("Bootable")
    @Nullable
    Boolean bootable;

    @SerializedName("PrimaryPartition")
    @Nullable
    Boolean primaryPartition;

    @SerializedName("BootPartition")
    @Nullable
    Boolean bootPartition;

    @SerializedName("DiskIndex")
    @Nullable
    Integer diskIndex;

    @SerializedName("Size")
    @Nullable
    Long size;

    @SerializedName("Type")
    @Nullable
    String type;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}