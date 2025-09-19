package com.ferrumx.system.storage.helper;

import com.ferrumx.system.storage.entity.DiskPartition;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiskPartitionHelper {

    @NotNull
    public List<DiskPartition> getDiskPartitions() {
        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_DiskPartition | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), DiskPartition.class);
    }

}
