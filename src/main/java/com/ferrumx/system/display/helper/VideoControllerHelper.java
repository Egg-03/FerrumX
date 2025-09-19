package com.ferrumx.system.display.helper;

import com.ferrumx.system.display.entity.VideoController;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideoControllerHelper {

    @NotNull
    public List<VideoController> getVideoControllers() {

        PowerShellResponse response = PowerShell.executeSingleCommand("Get-CimInstance Win32_VideoController | Select-Object * | ConvertTo-Json");
        return MapperUtil.mapToList(response.getCommandOutput(), VideoController.class);
    }
}
