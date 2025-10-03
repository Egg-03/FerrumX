package org.ferrumx.core.service.battery;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.battery.Battery;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching battery information from the system.
 * <p>
 * This class executes the {@link CimQuery#BATTERY_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link Battery} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * BatteryService batteryService = new BatteryService();
 * List<Battery> batteries = batteryService.getBatteries();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     List<Battery> batteries = batteryService.getBatteries(session);
 * }
 * }</pre>
 */

public class BatteryService {

    /**
     * Retrieves a list of batteries present on the system.
     * <p>
     * Each invocation creates and uses a short-lived
     * PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return a list of {@link Battery} objects representing the system's batteries.
     *         If no batteries are present, returns an empty list.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Battery> getBatteries() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BATTERY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Battery.class);
    }

    /**
     * Retrieves a list of batteries present on the system using the caller's
     * {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link Battery} objects representing the system's batteries.
     *         If no batteries are present, returns an empty list.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Battery> getBatteries(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.BATTERY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Battery.class);
    }
}