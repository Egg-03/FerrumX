package com.ferrumx.entity.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class NetworkAdapterConfiguration {

    @SerializedName("Index")
    @Nullable
    private Integer index;

    @SerializedName("Description")
    @Nullable
    private String description;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("SettingID")
    @Nullable
    private String settingId;

    @SerializedName("IPEnabled")
    @Nullable
    private Boolean ipEnabled;

    @SerializedName("IPAddress")
    @Nullable
    private List<String> ipAddress;

    @SerializedName("IPSubnet")
    @Nullable
    private List<String> ipSubnet;

    @SerializedName("DefaultIPGateway")
    @Nullable
    private List<String> defaultIpGateway;

    @SerializedName("DHCPEnabled")
    @Nullable
    private Boolean dhcpEnabled;

    @SerializedName("DHCPServer")
    @Nullable
    private String dhcpServer;

    @SerializedName("DHCPLeaseObtained")
    @Nullable
    private String dhcpLeaseObtained;

    @SerializedName("DHCPLeaseExpires")
    @Nullable
    private String dhcpLeaseExpires;

    @SerializedName("DNSHostName")
    @Nullable
    private String dnsHostName;

    @SerializedName("DNSServerSearchOrder")
    @Nullable
    private List<String> dnsServerSearchOrder;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}