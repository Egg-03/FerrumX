package com.ferrumx.service.display;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.display.Monitor;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import lombok.NonNull;

import java.util.List;

public class MonitorService {

    @NonNull
    public List<Monitor> getMonitors() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MONITOR_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Monitor.class);
    }
}
