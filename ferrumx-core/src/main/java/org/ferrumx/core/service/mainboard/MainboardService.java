package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.Mainboard;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Service class for fetching mainboard information from the system.
 * <p>
 * This class executes the {@link CimQuery#MAINBOARD_QUERY} PowerShell command
 * and maps the resulting JSON into a {@link Mainboard} object.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * MainboardService mainboardService = new MainboardService();
 * Optional<Mainboard> mainboard = mainboardService.getMainboard();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     MainboardService mainboardService = new MainboardService();
 *     Optional<Mainboard> mainboard = mainboardService.getMainboard(session);
 * }
 * }</pre>
 */

public class MainboardService {

    /**
     * Retrieves an {@link Optional} containing the system's mainboard information.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return an {@link Optional} of {@link Mainboard} representing the system's mainboard.
     *
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public Optional<Mainboard> getMainboard() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MAINBOARD_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Mainboard.class);
    }

    /**
     * Retrieves an {@link Optional} containing the system's mainboard information
     * using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return an {@link Optional} of {@link Mainboard} representing the system's mainboard.
     *
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public Optional<Mainboard> getMainboard(PowerShell powerShell) {
        PowerShellResponse response = powerShell.executeCommand(CimQuery.MAINBOARD_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Mainboard.class);
    }
}
