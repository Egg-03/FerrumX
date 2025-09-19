package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class Bios {

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Caption")
    @Nullable
    private String caption;

    @SerializedName("Manufacturer")
    @Nullable
    private String manufacturer;

    @SerializedName("ReleaseDate")
    @Nullable
    private String releaseDate;

    @SerializedName("SMBIOSPResent")
    @Nullable
    private Boolean smbiosPresent;

    @SerializedName("Status")
    @Nullable
    private String status;

    @SerializedName("Version")
    @Nullable
    private String version;

    @SerializedName("CurrentLanguage")
    @Nullable
    private String currentLanguage;

    @SerializedName("SMBIOSBIOSVersion")
    @Nullable
    private String smbiosBiosVersion;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}