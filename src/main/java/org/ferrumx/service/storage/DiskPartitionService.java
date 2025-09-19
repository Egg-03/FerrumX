package org.ferrumx.service.storage;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.storage.DiskPartition;
import org.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiskPartitionService {

    @NotNull
    public List<DiskPartition> getDiskPartitions() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.DISK_PARTITION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskPartition.class);
    }

}
