package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a BIOS entity on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_BIOS} WMI class.
 * </p>
 * <p>
 * Instances are inherently thread-safe and may be safely cached or shared across threads.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * Bios bios = Bios.builder()
 *     .name("BIOS Name")
 *     .version("1.2.3")
 *     .build();
 *
 * // Create a modified copy
 * Bios updated = bios.toBuilder()
 *     .version("1.2.4")
 *     .build();
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-bios">Win32_BIOS</a>
 * @since 2.0.0
 * @author Egg-03
 */

@Value
@Builder(toBuilder = true)
public class Bios {

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("ReleaseDate")
    @Nullable
    String releaseDate;

    @SerializedName("SMBIOSPresent")
    @Nullable
    Boolean smbiosPresent;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("CurrentLanguage")
    @Nullable
    String currentLanguage;

    @SerializedName("SMBIOSBIOSVersion")
    @Nullable
    String smbiosBiosVersion;

    @SerializedName("PrimaryBIOS")
    @Nullable
    Boolean primaryBios;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}