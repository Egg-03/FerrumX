package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a BIOS entity on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_BIOS} WMI class.
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
 * Bios bios = gson.fromJson(json, Bios.class);
 * Bios updated = bios.withVersion("1.2.3");
 * System.out.println(bios); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-bios">Win32_BIOS</a>
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