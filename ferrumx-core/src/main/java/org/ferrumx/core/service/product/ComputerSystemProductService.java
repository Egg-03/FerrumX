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
 */
public class ComputerSystemProductService {

    /**
     * Retrieves an {@link Optional} containing the computer system product information.
     *
     * @return an {@link Optional} of {@link ComputerSystemProduct} representing
     *         the computer system as a product. Returns {@link Optional#empty()} if no product information is detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    public Optional<ComputerSystemProduct> getProduct() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.COMPUTER_SYSTEM_PRODUCT.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), ComputerSystemProduct.class);
    }
}
