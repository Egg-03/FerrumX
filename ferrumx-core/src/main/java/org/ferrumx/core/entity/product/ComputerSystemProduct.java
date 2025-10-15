package org.ferrumx.core.entity.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

/**
 * Immutable representation of a computer product on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_ComputerSystemProduct} WMI class.
 * </p>
 * <p>
 * Instances are thread-safe and may be safely cached or shared across threads.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Build a new ComputerSystemProduct instance
 * ComputerSystemProduct product = ComputerSystemProduct.builder()
 *     .caption("Workstation PC")
 *     .description("High-end office workstation")
 *     .identifyingNumber("ID-001")
 *     .name("User-PC")
 *     .skuNumber("PROD-1234")
 *     .vendor("Default Vendor")
 *     .version("1.0")
 *     .uuid("550e8400-e29b-41d4-a716-446655440000")
 *     .build();
 *
 * // Create a modified copy
 * ComputerSystemProduct updated = product.toBuilder()
 *     .skuNumber("PROD-5678")
 *     .build();
 *
 * }</pre>
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-computersystemproduct">Win32_ComputerSystemProduct</a>
 * @since 2.0.0
 * @author Egg-03
 */

@Value
@Builder(toBuilder = true)
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