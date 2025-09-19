package com.ferrumx.system.processor.helper;

import com.ferrumx.system.processor.entity.ProcessorCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

public class ProcessorCacheHelper {

    @NotNull
    public List<ProcessorCache> getProcessorCaches() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_CacheMemory | Select-Object * | ConvertTo-Json");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProcessorCache>>() {}.getType();
        return gson.fromJson(response.getCommandOutput(), listType);
    }

}
