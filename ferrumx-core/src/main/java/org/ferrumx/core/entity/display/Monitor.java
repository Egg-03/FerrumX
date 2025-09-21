package org.ferrumx.core.entity.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a monitor device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_DesktopMonitor} WMI class.
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
 * Monitor monitor = gson.fromJson(json, Monitor.class);
 * Monitor scaled = monitor.withPixelsPerXLogicalInch(120);
 * System.out.println(monitor); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-desktopmonitor">Win32_DesktopMonitor</a>
 */

@Value
@With
public class Monitor {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("PNPDeviceID")
    @Nullable
    String pnpDeviceId;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("MonitorManufacturer")
    @Nullable
    String monitorManufacturer;

    @SerializedName("MonitorType")
    @Nullable
    String monitorType;

    @SerializedName("PixelsPerXLogicalInch")
    @Nullable
    Integer pixelsPerXLogicalInch;

    @SerializedName("PixelsPerYLogicalInch")
    @Nullable
    Integer pixelsPerYLogicalInch;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}