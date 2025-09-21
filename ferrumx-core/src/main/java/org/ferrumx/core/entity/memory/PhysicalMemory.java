package org.ferrumx.core.entity.memory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a RAM unit on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_PhysicalMemory} WMI class.
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
 * PhysicalMemory ram = gson.fromJson(json, PhysicalMemory.class);
 * PhysicalMemory upgraded = ram.withCapacity(16L * 1024 * 1024 * 1024);
 * System.out.println(ram); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-physicalmemory">Win32_PhysicalMemory</a>
 */

@Value
@With
public class PhysicalMemory {

    @SerializedName("Tag")
    @Nullable
    String tag;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("Model")
    @Nullable
    String model;

    @SerializedName("OtherIdentifyingInfo")
    @Nullable
    String otherIdentifyingInfo;

    @SerializedName("PartNumber")
    @Nullable
    String partNumber;

    @SerializedName("FormFactor")
    @Nullable
    Integer formFactor;

    @SerializedName("BankLabel")
    @Nullable
    String bankLabel;

    @SerializedName("Capacity")
    @Nullable
    Long capacity;

    @SerializedName("DataWidth")
    @Nullable
    Integer dataWidth;

    @SerializedName("Speed")
    @Nullable
    Integer speed;

    @SerializedName("ConfiguredClockSpeed")
    @Nullable
    Integer configuredClockSpeed;

    @SerializedName("DeviceLocator")
    @Nullable
    String deviceLocator;

    @SerializedName("SerialNumber")
    @Nullable
    String serialNumber;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
