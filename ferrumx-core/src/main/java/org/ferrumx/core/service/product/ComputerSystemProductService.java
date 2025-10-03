package org.ferrumx.core.service.product;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.product.ComputerSystemProduct;
import org.ferrumx.core.util.MapperUtil;

import java.util.Optional;

/**
 * Service class for fetching the system product information.
 * <p>
 * This class executes the {@link CimQuery#COMPUTER_SYSTEM_PRODUCT} PowerShell command
 * and maps the resulting JSON into an {@link Optional} {@link ComputerSystemProduct} object.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * ComputerSystemProductService productService = new ComputerSystemProductService();
 * Optional<ComputerSystemProduct> product = productService.getProduct();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     ComputerSystemProductService productService = new ComputerSystemProductService();
 *     Optional<ComputerSystemProduct> product = productService.getProduct(session);
 * }
 * }</pre>
 */

public class ComputerSystemProductService {

    /**
     * Retrieves an {@link Optional} containing the computer system product information.
     * <p>
     * Not thread-safe.
     *
     * @return an {@link Optional} of {@link ComputerSystemProduct} representing
     *         the computer system as a product. Returns {@link Optional#empty()} if no product information is detected.
     */
    public Optional<ComputerSystemProduct> getProduct() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.COMPUTER_SYSTEM_PRODUCT.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), ComputerSystemProduct.class);
    }

    /**
     * Retrieves an {@link Optional} containing the computer system product information
     * using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return an {@link Optional} of {@link ComputerSystemProduct} representing
     *         the computer system as a product. Returns {@link Optional#empty()} if no product information is detected.
     */
    public Optional<ComputerSystemProduct> getProduct(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.COMPUTER_SYSTEM_PRODUCT.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), ComputerSystemProduct.class);
    }
}
