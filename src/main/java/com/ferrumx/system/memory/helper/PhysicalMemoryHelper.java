package com.ferrumx.system.memory.helper;

import com.ferrumx.system.memory.entity.PhysicalMemory;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PhysicalMemoryHelper {

    @NotNull
    public List<PhysicalMemory> getPhysicalMemory() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_PhysicalMemory | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), PhysicalMemory.class);
    }

}
