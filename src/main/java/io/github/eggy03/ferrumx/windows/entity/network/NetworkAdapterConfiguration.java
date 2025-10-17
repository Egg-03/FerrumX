package io.github.eggy03.ferrumx.windows.entity.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of a network adapter configuration on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_NetworkAdapterConfiguration} WMI class.
 * </p>
 * <p>
 * Instances are thread-safe and may be safely cached or shared across threads.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Create a new configuration using the builder
 * NetworkAdapterConfiguration config = NetworkAdapterConfiguration.builder()
 *     .index(1)
 *     .description("Ethernet Adapter")
 *     .ipEnabled(true)
 *     .ipAddress(List.of("192.168.1.100"))
 *     .dnsServerSearchOrder(List.of("8.8.8.8", "8.8.4.4"))
 *     .build();
 *
 * // Create a modified copy using the builder
 * NetworkAdapterConfiguration updated = config.toBuilder()
 *     .description("Updated Ethernet Adapter")
 *     .build();
 *
 * }</pre>
 *
 * See {@link NetworkAdapter} for the corresponding adapter entity
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-networkadapterconfiguration">Win32_NetworkAdapterConfiguration</a>
 * @since 2.0.0
 * @author Egg-03
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