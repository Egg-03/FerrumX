package com.ferrumx.service.display;

import com.ferrumx.constant.CimQuery;
import com.ferrumx.entity.display.VideoController;
import com.ferrumx.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideoControllerService {

    @NotNull
    public List<VideoController> getVideoControllers() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.VIDEO_CONTROLLER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), VideoController.class);
    }
}
