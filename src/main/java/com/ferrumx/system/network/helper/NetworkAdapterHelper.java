package com.ferrumx.system.network.helper;

import com.ferrumx.system.network.entity.NetworkAdapter;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetworkAdapterHelper {

    @Nullable
    public List<NetworkAdapter> getNetworkAdapters() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_NetworkAdapter | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }
}
