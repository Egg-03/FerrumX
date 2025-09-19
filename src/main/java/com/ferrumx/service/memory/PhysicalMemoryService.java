package com.ferrumx.service.memory;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.memory.PhysicalMemory;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PhysicalMemoryService {

    @NotNull
    public List<PhysicalMemory> getPhysicalMemory() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PHYSICAL_MEMORY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), PhysicalMemory.class);
    }

}
