package org.ferrumx.service.mainboard;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.mainboard.Bios;
import org.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BiosService {

    @NotNull
    public Optional<Bios> getBios() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BIOS_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Bios.class);
    }
}
