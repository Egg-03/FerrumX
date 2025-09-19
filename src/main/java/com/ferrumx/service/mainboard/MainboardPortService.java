package com.ferrumx.service.mainboard;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.mainboard.MainboardPort;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainboardPortService {

    @NotNull
    public List<MainboardPort> getMainboardPorts() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MAINBOARD_PORT_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), MainboardPort.class);
    }
}
