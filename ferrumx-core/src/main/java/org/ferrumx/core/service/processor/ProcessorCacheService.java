package org.ferrumx.core.service.processor;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.processor.ProcessorCache;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.util.MapperUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProcessorCacheService {

    @NotNull
    public List<ProcessorCache> getProcessorCaches() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_CACHE_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), ProcessorCache.class);
    }

}
