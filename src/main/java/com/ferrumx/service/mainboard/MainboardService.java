package com.ferrumx.service.mainboard;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.mainboard.Mainboard;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MainboardService {

    @NotNull
    public Optional<Mainboard> getMainboard() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MAINBOARD_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Mainboard.class);
    }
}
