package org.ferrumx.core.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Motherboard Port device on a Windows system.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_PortConnector WMI class.
 */
@Data
public class MainboardPort {

    @SerializedName("Tag")
    @Nullable
    private String tag;

    @SerializedName("ExternalReferenceDesignator")
    @Nullable
    private String externalReferenceDesignator;

    @SerializedName("InternalReferenceDesignator")
    @Nullable
    private String internalReferenceDesignator;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}