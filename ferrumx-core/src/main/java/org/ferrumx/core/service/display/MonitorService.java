package org.ferrumx.core.service.display;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.display.Monitor;
import org.ferrumx.core.util.MapperUtil;
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
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getMonitors()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * MonitorService monitorService = new MonitorService();
 * List<Monitor> monitors = monitorService.getMonitors();
 * monitors.forEach(System.out::println);
 * }</pre>
 */

public class MonitorService {

    /**
     * Retrieves a non-null list of monitors connected to the system.
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
}
