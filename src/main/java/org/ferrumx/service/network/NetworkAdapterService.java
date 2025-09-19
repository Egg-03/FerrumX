package org.ferrumx.service.network;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.network.NetworkAdapter;
import org.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NetworkAdapterService {

    @NotNull
    public List<NetworkAdapter> getNetworkAdapters() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }
}
