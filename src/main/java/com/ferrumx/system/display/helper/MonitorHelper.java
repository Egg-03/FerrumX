package com.ferrumx.system.display.helper;

import com.ferrumx.system.display.entity.Monitor;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonitorHelper {

    @Nullable
    public List<Monitor> getMonitors() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_DesktopMonitor | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), Monitor.class);
    }
}
