package com.ferrumx.system.processor.helper;

import com.ferrumx.system.processor.entity.Processor;
import com.google.gson.Gson;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.Nullable;

public class ProcessorHelper {

    @Nullable
    public Processor getProcessor() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_Processor | Select-Object * | ConvertTo-Json");
        Gson gson = new Gson();
        return gson.fromJson(response.getCommandOutput(), Processor.class);
    }
}
