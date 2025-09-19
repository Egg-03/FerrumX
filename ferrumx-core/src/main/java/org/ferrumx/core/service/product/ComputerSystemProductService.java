package org.ferrumx.core.service.product;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.product.ComputerSystemProduct;
import org.ferrumx.core.util.MapperUtil;

import java.util.Optional;

public class ComputerSystemProductService {

    public Optional<ComputerSystemProduct> getProduct() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.COMPUTER_SYSTEM_PRODUCT.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), ComputerSystemProduct.class);
    }
}
