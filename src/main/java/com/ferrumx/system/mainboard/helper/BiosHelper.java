package com.ferrumx.system.mainboard.helper;

import com.ferrumx.system.mainboard.entity.Bios;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

public class BiosHelper {

    @Nullable
    public Bios getBios() {
        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_BIOS | Where-Object { $_.PrimaryBIOS -eq $true } | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToObject(response.getCommandOutput(), Bios.class);
    }
}
