package org.ferrumx.core.service.battery;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.battery.Battery;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BatteryService {

    @NotNull
    public List<Battery> getBatteries() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BATTERY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Battery.class);
    }
}