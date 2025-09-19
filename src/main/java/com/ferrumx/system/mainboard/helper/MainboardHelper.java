package com.ferrumx.system.mainboard.helper;

import com.ferrumx.system.mainboard.entity.Mainboard;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MainboardHelper {

    @NotNull
    public Optional<Mainboard> getMainboard() {
        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_Baseboard | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToObject(response.getCommandOutput(), Mainboard.class);
    }
}
