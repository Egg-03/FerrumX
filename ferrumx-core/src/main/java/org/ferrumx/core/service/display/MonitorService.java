package org.ferrumx.core.service.display;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.display.Monitor;
import org.ferrumx.core.mapping.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import lombok.NonNull;

import java.util.List;

/**
 * Service class for fetching monitor information from the system.
 * <p>
 * This class executes the {@link CimQuery#MONITOR_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link Monitor} objects.
 * <p>
 * <h2>Thread safety</h2>
 * Methods of class are not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * MonitorService monitorService = new MonitorService();
 * List<Monitor> monitors = monitorService.getMonitors();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     List<Monitor> monitors = monitorService.getMonitors(session);
 * }
 * }</pre>
 */

public class MonitorService {

    /**
     * Retrieves a list of monitors connected to the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     *
     * @return a list of {@link Monitor} objects representing connected monitors.
     *         Returns an empty list if no monitors are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NonNull
    public List<Monitor> getMonitors() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MONITOR_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Monitor.class);
    }

    /**
     * Retrieves a list of monitors connected to the system using the caller's
     * {@link PowerShell} session.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link Monitor} objects representing connected monitors.
     *         Returns an empty list if no monitors are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NonNull
    public List<Monitor> getMonitors(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.MONITOR_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Monitor.class);
    }
}
