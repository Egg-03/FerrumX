package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.Bios;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BiosService {

    @NotNull
    public List<Bios> getBios() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BIOS_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Bios.class);
    }
}
