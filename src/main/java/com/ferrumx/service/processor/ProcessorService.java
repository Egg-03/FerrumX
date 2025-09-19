package com.ferrumx.service.processor;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.processor.Processor;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ProcessorService {

    @NotNull
    public Optional<Processor> getProcessor() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Processor.class);
    }
}
