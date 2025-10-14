package org.ferrumx.core.service.display;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.display.VideoController;
import org.ferrumx.core.mapping.MapperUtil;
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
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * VideoControllerService videoControllerService = new VideoControllerService();
 * List<VideoController> controllers = videoControllerService.getVideoControllers();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     VideoControllerService videoControllerService = new VideoControllerService();
 *     List<VideoController> controllers = videoControllerService.getVideoControllers(session);
 * }
 * }</pre>
 */

public class VideoControllerService {

    /**
     * Retrieves a list of video controllers (GPUs) present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return a list of {@link VideoController} objects representing the video controllers.
     *         Returns an empty list if none are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<VideoController> getVideoControllers() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.VIDEO_CONTROLLER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), VideoController.class);
    }

    /**
     * Retrieves a list of video controllers (GPUs) present in the system using the caller's
     * {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link VideoController} objects representing the video controllers.
     *         Returns an empty list if none are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<VideoController> getVideoControllers(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.VIDEO_CONTROLLER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), VideoController.class);
    }
}
