package org.ferrumx.core.entity.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents a Computer Product on a Windows system.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_ComputerSystemProduct WMI class.
 */
@Data
public class ComputerSystemProduct {

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Description")
    private String description;

    @SerializedName("IdentifyingNumber")
    private String identifyingNumber;

    @SerializedName("Name")
    private String name;

    @SerializedName("SKUNumber")
    private String skuNumber;

    @SerializedName("Vendor")
    private String vendor;

    @SerializedName("Version")
    private String version;

    @SerializedName("UUID")
    private String uuid;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

