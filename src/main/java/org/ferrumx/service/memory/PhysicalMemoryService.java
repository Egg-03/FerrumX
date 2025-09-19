package org.ferrumx.service.memory;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.memory.PhysicalMemory;
import org.ferrumx.util.MapperUtil;
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
