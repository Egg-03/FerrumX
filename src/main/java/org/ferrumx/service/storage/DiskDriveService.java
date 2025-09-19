package org.ferrumx.service.storage;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.storage.DiskDrive;
import org.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiskDriveService {

    @NotNull
    public List<DiskDrive> getDiskDrives() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.DISK_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskDrive.class);
    }
}
