package org.ferrumx.service.display;

import org.ferrumx.constant.CimQuery;
import org.ferrumx.entity.display.VideoController;
import org.ferrumx.util.MapperUtil;
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
