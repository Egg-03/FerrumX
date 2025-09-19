package com.ferrumx.system.storage.helper;

import com.ferrumx.system.storage.entity.DiskDrive;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiskDriveHelper {

    @NotNull
    public List<DiskDrive> getDiskDrives() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_DiskDrive | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), DiskDrive.class);
    }
}
