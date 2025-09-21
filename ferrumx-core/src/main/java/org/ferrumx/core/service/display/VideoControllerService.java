package org.ferrumx.core.service.display;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.display.VideoController;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching video controller (GPU) information from the system.
 * <p>
 * This class executes the {@link CimQuery#VIDEO_CONTROLLER_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link VideoController} objects.
 * <p>
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getVideoControllers()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * VideoControllerService gpuService = new VideoControllerService();
 * List<VideoController> controllers = gpuService.getVideoControllers();
 * controllers.forEach(System.out::println);
 * }</pre>
 */

public class VideoControllerService {

    /**
     * Retrieves a non-null list of video controllers (GPUs) present in the system.
     *
     * @return a list of {@link VideoController} objects representing the video controllers.
     *         Returns an empty list if no controllers are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<VideoController> getVideoControllers() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.VIDEO_CONTROLLER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), VideoController.class);
    }
}
