package org.ferrumx.core.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a CPU device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_Processor} WMI class.
 * Values are captured at query time and do not automatically update.
 * <p>
 * This class is annotated with Lombok {@link lombok.Value} to enforce immutability
 * <p>
 * To derive a modified copy, use the generated {@code withXxx(...)} methods
 * (enabled by Lombok {@link lombok.With}). This allows safe copy-on-write semantics
 * without breaking immutability.
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
 * Processor cpu = gson.fromJson(json, Processor.class);
 * Processor updated = cpu.withThreadCount(16);
 * System.out.println(cpu); // Pretty-printed JSON
 * }</pre>
 *
 * {@link ProcessorCache} for related cache information
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-processor">Win32_Processor</a>
 */

@Value
@With
public class Processor {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("NumberOfCores")
    @Nullable
    Integer numberOfCores;

    @SerializedName("ThreadCount")
    @Nullable
    Integer threadCount;

    @SerializedName("NumberOfLogicalProcessors")
    @Nullable
    Integer numberOfLogicalProcessors;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("AddressWidth")
    @Nullable
    Integer addressWidth;

    @SerializedName("L2CacheSize")
    @Nullable
    Integer l2CacheSize;

    @SerializedName("L3CacheSize")
    @Nullable
    Integer l3CacheSize;

    @SerializedName("MaxClockSpeed")
    @Nullable
    Integer maxClockSpeed;

    @SerializedName("ExtClock")
    @Nullable
    Integer extClock;

    @SerializedName("SocketDesignation")
    @Nullable
    String socketDesignation;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("Family")
    @Nullable
    Integer family;

    @SerializedName("Stepping")
    @Nullable
    Integer stepping;

    @SerializedName("VirtualizationFirmwareEnabled")
    @Nullable
    Boolean virtualizationFirmwareEnabled;

    @SerializedName("ProcessorId")
    @Nullable
    String processorId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}


