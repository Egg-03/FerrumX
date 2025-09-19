package com.ferrumx.system.os.helper;

import com.ferrumx.system.os.entity.OperatingSystem;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OperatingSystemHelper {

    @NotNull
    public List<OperatingSystem> getOperatingSystems() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_OperatingSystem | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), OperatingSystem.class);
    }
}
