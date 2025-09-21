package org.ferrumx.core.entity.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import lombok.With;

/**
 * Immutable representation of a computer product on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_ComputerSystemProduct} WMI class.
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
 * ComputerSystemProduct product = gson.fromJson(json, ComputerSystemProduct.class);
 * ComputerSystemProduct updated = product.withSkuNumber("PROD-1234");
 * System.out.println(product); // Pretty-printed JSON
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-computersystemproduct">Win32_ComputerSystemProduct</a>
 */

@Value
@With
public class ComputerSystemProduct {

    @SerializedName("Caption")
    String caption;

    @SerializedName("Description")
    String description;

    @SerializedName("IdentifyingNumber")
    String identifyingNumber;

    @SerializedName("Name")
    String name;

    @SerializedName("SKUNumber")
    String skuNumber;

    @SerializedName("Vendor")
    String vendor;

    @SerializedName("Version")
    String version;

    @SerializedName("UUID")
    String uuid;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

