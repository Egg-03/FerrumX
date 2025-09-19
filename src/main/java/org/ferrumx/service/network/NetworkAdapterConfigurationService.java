package org.ferrumx.service.network;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.network.NetworkAdapterConfiguration;
import org.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NetworkAdapterConfigurationService {

    @NotNull
    public List<NetworkAdapterConfiguration> getNetworkAdapterConfiguration() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_CONFIGURATION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapterConfiguration.class);
    }

}
