package com.ferrumx.system.network.helper;

import com.ferrumx.system.network.entity.NetworkAdapterConfiguration;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetworkAdapterConfigurationHelper {

    @Nullable
    public List<NetworkAdapterConfiguration> getNetworkAdapterConfiguration() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_NetworkAdapterConfiguration | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapterConfiguration.class);
    }

}
