package com.ferrumx.system.mainboard.helper;

import com.ferrumx.system.mainboard.entity.MainboardPort;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MainboardPortHelper {

    @Nullable
    public List<MainboardPort> getMainboardPorts() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_PortConnector | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), MainboardPort.class);
    }
}
