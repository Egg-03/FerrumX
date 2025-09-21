package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a motherboard port device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_PortConnector} WMI class.
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
 * MainboardPort port = gson.fromJson(json, MainboardPort.class);
 * MainboardPort updated = port.withExternalReferenceDesignator("USB3_0");
 * System.out.println(port); // Pretty-printed JSON
 * }</pre>
 *
 * {@link Mainboard} for the containing mainboard details.
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-portconnector">Win32_PortConnector</a>
 */

@Value
@With
public class MainboardPort {

    @SerializedName("Tag")
    @Nullable
    String tag;

    @SerializedName("ExternalReferenceDesignator")
    @Nullable
    String externalReferenceDesignator;

    @SerializedName("InternalReferenceDesignator")
    @Nullable
    String internalReferenceDesignator;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}