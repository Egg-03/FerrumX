package org.ferrumx.core.entity.battery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of a battery device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_Battery} WMI class.
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
 * Battery battery = gson.fromJson(json, Battery.class);
 * Battery depleted = battery.withEstimatedChargeRemaining(0);
 * System.out.println(battery); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-battery">Win32_Battery</a>
 */

@Value
@Builder(toBuilder = true)
public class Battery {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("Description")
    @Nullable
    String description;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("PowerManagementCapabilities")
    @Nullable
    List<String> powerManagementCapabilities;

    @SerializedName("PowerManagementSupported")
    @Nullable
    Boolean powerManagementSupported;

    @SerializedName("BatteryStatus")
    @Nullable
    Integer batteryStatus;

    @SerializedName("Chemistry")
    @Nullable
    Integer chemistry;

    @SerializedName("DesignCapacity")
    @Nullable
    Integer designCapacity;

    @SerializedName("DesignVoltage")
    @Nullable
    Integer designVoltage;

    @SerializedName("EstimatedChargeRemaining")
    @Nullable
    Integer estimatedChargeRemaining;

    @SerializedName("EstimatedRunTime")
    @Nullable
    Integer estimatedRunTime;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}