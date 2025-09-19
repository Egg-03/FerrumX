package com.ferrumx.system.processor.helper;

import com.ferrumx.system.processor.entity.Processor;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ProcessorHelper {

    @NotNull
    public Optional<Processor> getProcessor() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_Processor | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToObject(response.getCommandOutput(), Processor.class);
    }
}
