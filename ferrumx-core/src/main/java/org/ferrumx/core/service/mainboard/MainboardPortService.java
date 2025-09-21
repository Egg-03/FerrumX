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
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getMainboardPorts()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * MainboardPortService portService = new MainboardPortService();
 * List<MainboardPort> ports = portService.getMainboardPorts();
 * ports.forEach(System.out::println);
 * }</pre>
 */

public class MainboardPortService {

    /**
     * Retrieves a non-null list of mainboard ports present in the system.
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
}
