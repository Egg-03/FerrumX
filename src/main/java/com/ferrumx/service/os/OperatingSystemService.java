package com.ferrumx.service.os;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.os.OperatingSystem;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OperatingSystemService {

    @NotNull
    public List<OperatingSystem> getOperatingSystems() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.OPERATING_SYSTEM_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), OperatingSystem.class);
    }
}
