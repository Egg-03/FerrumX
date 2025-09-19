package org.ferrumx.service.processor;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.processor.Processor;
import org.ferrumx.util.MapperUtil;
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
