package org.ferrumx.core.entity.memory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a RAM unit on a Windows system.
 * <p>
 * Fields correspond to the properties retrieved from the Win32_PhysicalMemory WMI class.
 */
@Data
public class PhysicalMemory {

    @SerializedName("Tag")
    @Nullable
    private String tag;

    @SerializedName("Name")
    @Nullable
    private String name;

    @SerializedName("Manufacturer")
    @Nullable
    private String manufacturer;

    @SerializedName("Model")
    @Nullable
    private String model;

    @SerializedName("OtherIdentifyingInfo")
    @Nullable
    private String otherIdentifyingInfo;

    @SerializedName("PartNumber")
    @Nullable
    private String partNumber;

    @SerializedName("FormFactor")
    @Nullable
    private Integer formFactor;

    @SerializedName("BankLabel")
    @Nullable
    private String bankLabel;

    @SerializedName("Capacity")
    @Nullable
    private Long capacity;

    @SerializedName("DataWidth")
    @Nullable
    private Integer dataWidth;

    @SerializedName("Speed")
    @Nullable
    private Integer speed;

    @SerializedName("ConfiguredClockSpeed")
    @Nullable
    private Integer configuredClockSpeed;

    @SerializedName("DeviceLocator")
    @Nullable
    private String deviceLocator;

    @SerializedName("SerialNumber")
    @Nullable
    private String serialNumber;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
