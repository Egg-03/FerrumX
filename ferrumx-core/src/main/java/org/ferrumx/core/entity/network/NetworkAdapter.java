package org.ferrumx.core.entity.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a network adapter device on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_NetworkAdapter} WMI class.
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
 * NetworkAdapter adapter = gson.fromJson(json, NetworkAdapter.class);
 * NetworkAdapter updated = adapter.withNetEnabled(true);
 * System.out.println(adapter); // Pretty-printed JSON
 * }</pre>
 *
 * {@link NetworkAdapterConfiguration} for related network configuration details
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-networkadapter">Win32_NetworkAdapter</a>
 */

@Value
@With
public class NetworkAdapter {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Index")
    @Nullable
    Integer index;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("Description")
    @Nullable
    String description;

    @SerializedName("PNPDeviceID")
    @Nullable
    String pnpDeviceId;

    @SerializedName("MACAddress")
    @Nullable
    String macAddress;

    @SerializedName("Installed")
    @Nullable
    Boolean installed;

    @SerializedName("NetEnabled")
    @Nullable
    Boolean netEnabled;

    @SerializedName("NetConnectionID")
    @Nullable
    String netConnectionId;

    @SerializedName("PhysicalAdapter")
    @Nullable
    Boolean physicalAdapter;

    @SerializedName("TimeOfLastReset")
    @Nullable
    String timeOfLastReset;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

