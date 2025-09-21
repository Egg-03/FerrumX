package org.ferrumx.core.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a processor cache (e.g., L1, L2, L3) on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_CacheMemory} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * This class is annotated with Lombok {@link lombok.Value} to enforce immutability
 * <p>
 * Additionally, Lombok {@link lombok.With} generates {@code withXxx(...)} methods
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
 * ProcessorCache l2 = gson.fromJson(json, ProcessorCache.class);
 * ProcessorCache resized = l2.withInstalledSize(512);
 * System.out.println(l2); // Pretty-printed JSON
 * }</pre>
 *
 * {@link Processor} for related CPU information
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-cachememory">Win32_CacheMemory</a>
 */

@Value
@With
public class ProcessorCache {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Purpose")
    @Nullable
    String purpose;

    @SerializedName("InstalledSize")
    @Nullable
    Integer installedSize;

    @SerializedName("Associativity")
    @Nullable
    Integer associativity;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}