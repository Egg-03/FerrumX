package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.MainboardPort;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching mainboard port information from the system.
 * <p>
 * This class executes the {@link CimQuery#MAINBOARD_PORT_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link MainboardPort} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * MainboardPortService portService = new MainboardPortService();
 * List<MainboardPort> ports = portService.getMainboardPorts();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     MainboardPortService portService = new MainboardPortService();
 *     List<MainboardPort> ports = portService.getMainboardPorts(session);
 * }
 * }</pre>
 */

public class MainboardPortService {

    /**
     * Retrieves a list of mainboard ports present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return a list of {@link MainboardPort} objects representing the system's mainboard ports.
     *         Returns an empty list if no ports are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<MainboardPort> getMainboardPorts() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MAINBOARD_PORT_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), MainboardPort.class);
    }

    /**
     * Retrieves a list of mainboard ports present in the system using the caller's
     * {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link MainboardPort} objects representing the system's mainboard ports.
     *         Returns an empty list if no ports are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<MainboardPort> getMainboardPorts(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.MAINBOARD_PORT_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), MainboardPort.class);
    }
}
