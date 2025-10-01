package org.ferrumx.core.entity.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of a network adapter configuration on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_NetworkAdapterConfiguration} WMI class.
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
 * NetworkAdapterConfiguration config = gson.fromJson(json, NetworkAdapterConfiguration.class);
 * NetworkAdapterConfiguration updated = config.withIpEnabled(true);
 * System.out.println(config); // Pretty-printed JSON
 * }</pre>
 *
 * {@link NetworkAdapter} for the corresponding adapter entity
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-networkadapterconfiguration">Win32_NetworkAdapterConfiguration</a>
 */

@Value
@Builder(toBuilder = true)
public class NetworkAdapterConfiguration {

    @SerializedName("Index")
    @Nullable
    Integer index;

    @SerializedName("Description")
    @Nullable
    String description;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("SettingID")
    @Nullable
    String settingId;

    @SerializedName("IPEnabled")
    @Nullable
    Boolean ipEnabled;

    @SerializedName("IPAddress")
    @Nullable
    List<String> ipAddress;

    @SerializedName("IPSubnet")
    @Nullable
    List<String> ipSubnet;

    @SerializedName("DefaultIPGateway")
    @Nullable
    List<String> defaultIpGateway;

    @SerializedName("DHCPEnabled")
    @Nullable
    Boolean dhcpEnabled;

    @SerializedName("DHCPServer")
    @Nullable
    String dhcpServer;

    @SerializedName("DHCPLeaseObtained")
    @Nullable
    String dhcpLeaseObtained;

    @SerializedName("DHCPLeaseExpires")
    @Nullable
    String dhcpLeaseExpires;

    @SerializedName("DNSHostName")
    @Nullable
    String dnsHostName;

    @SerializedName("DNSServerSearchOrder")
    @Nullable
    List<String> dnsServerSearchOrder;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}