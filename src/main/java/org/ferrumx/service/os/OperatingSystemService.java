package org.ferrumx.service.os;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.os.OperatingSystem;
import org.ferrumx.util.MapperUtil;
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
