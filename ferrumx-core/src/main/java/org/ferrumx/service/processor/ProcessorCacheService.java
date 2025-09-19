package org.ferrumx.service.processor;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.processor.ProcessorCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

public class ProcessorCacheService {

    @NotNull
    public List<ProcessorCache> getProcessorCaches() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_CACHE_QUERY.getQuery());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProcessorCache>>() {}.getType();
        return gson.fromJson(response.getCommandOutput(), listType);
    }

}
