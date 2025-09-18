package com.ferrumx.system.network.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class NetworkAdapterConfiguration {

    @SerializedName("Index")
    private int index;

    @SerializedName("Description")
    private String description;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("SettingID")
    private String settingId;

    @SerializedName("IPEnabled")
    private boolean ipEnabled;

    @SerializedName("IPAddress")
    private List<String> ipAddress;

    @SerializedName("IPSubnet")
    private List<String> ipSubnet;

    @SerializedName("DefaultIPGateway")
    private List<String> defaultIpGateway;

    @SerializedName("DHCPEnabled")
    private boolean dhcpEnabled;

    @SerializedName("DHCPServer")
    private String dhcpServer;

    @SerializedName("DHCPLeaseObtained")
    private String dhcpLeaseObtained;

    @SerializedName("DHCPLeaseExpires")
    private String dhcpLeaseExpires;

    @SerializedName("DNSHostName")
    private String dnsHostName;

    @SerializedName("DNSServerSearchOrder")
    private List<String> dnsServerSearchOrder;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
