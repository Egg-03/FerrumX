package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a motherboard device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_Baseboard} WMI class.
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
 * Mainboard board = gson.fromJson(json, Mainboard.class);
 * Mainboard updated = board.withSerialNumber("ABC123456");
 * System.out.println(board); // Pretty-printed JSON
 * }</pre>
 *
 * {@link MainboardPort} for the containing mainboard port details.
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-baseboard">Win32_Baseboard</a>
 */

@Value
@With
public class Mainboard {

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("Model")
    @Nullable
    String model;

    @SerializedName("Product")
    @Nullable
    String product;

    @SerializedName("SerialNumber")
    @Nullable
    String serialNumber;

    @SerializedName("Version")
    @Nullable
    String version;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}