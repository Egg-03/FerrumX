package org.ferrumx.core.entity.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a GPU device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_VideoController} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * Instances are inherently thread-safe and may be safely cached or shared across threads.
 * <p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * // Build a new VideoController instance
 * VideoController gpu = VideoController.builder()
 *     .deviceId("GPU1")
 *     .name("NVIDIA GeForce RTX")
 *     .currentRefreshRate(60)
 *     .build();
 *
 * // Modify using toBuilder (copy-on-write)
 * VideoController updated = gpu.toBuilder()
 *     .currentRefreshRate(144)
 *     .build();
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-videocontroller">Win32_VideoController</a>
 */

@Value
@Builder(toBuilder = true)
public class VideoController {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("PNPDeviceID")
    @Nullable
    String pnpDeviceId;

    @SerializedName("CurrentBitsPerPixel")
    @Nullable
    Integer currentBitsPerPixel;

    @SerializedName("CurrentHorizontalResolution")
    @Nullable
    Integer currentHorizontalResolution;

    @SerializedName("CurrentVerticalResolution")
    @Nullable
    Integer currentVerticalResolution;

    @SerializedName("CurrentRefreshRate")
    @Nullable
    Integer currentRefreshRate;

    @SerializedName("MaxRefreshRate")
    @Nullable
    Integer maxRefreshRate;

    @SerializedName("MinRefreshRate")
    @Nullable
    Integer minRefreshRate;

    @SerializedName("AdapterDACType")
    @Nullable
    String adapterDacType;

    @SerializedName("AdapterRAM")
    @Nullable
    Long adapterRam;

    @SerializedName("DriverDate")
    @Nullable
    String driverDate;

    @SerializedName("DriverVersion")
    @Nullable
    String driverVersion;

    @SerializedName("VideoProcessor")
    @Nullable
    String videoProcessor;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}