package io.github.eggy03.ferrumx.core.entity.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a logical disk partition on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_DiskPartition} WMI class.
 * </p>
 * <p>
 * Instances are thread-safe and may be safely cached or shared across threads.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Build a new DiskPartition
 * DiskPartition partition = DiskPartition.builder()
 *     .deviceId("Disk0\\Partition1")
 *     .name("System Reserved")
 *     .description("EFI System Partition")
 *     .blockSize(512L)
 *     .numberOfBlocks(131072L)
 *     .bootable(true)
 *     .primaryPartition(true)
 *     .bootPartition(true)
 *     .diskIndex(0)
 *     .size(67108864L)
 *     .type("EFI")
 *     .build();
 *
 * // Create a modified copy
 * DiskPartition resizedPartition = partition.toBuilder()
 *     .size(134217728L)
 *     .build();
 *
 * }</pre>
 *
 * {@link DiskDrive} contains additional information about the physical disk.
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-diskpartition">Win32_DiskPartition</a>
 * @since 2.0.0
 * @author Egg-03
 */

@Value
@Builder(toBuilder = true)
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