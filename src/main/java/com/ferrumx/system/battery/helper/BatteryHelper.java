package com.ferrumx.system.battery.helper;

import com.ferrumx.system.battery.entity.Battery;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BatteryHelper {

    @NotNull
    public List<Battery> getBatteries() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_Battery | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), Battery.class);
    }
}

