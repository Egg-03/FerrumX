package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.Bios;
import org.ferrumx.core.mapping.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching BIOS information from the system.
 * <p>
 * This class executes the {@link CimQuery#BIOS_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link Bios} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * BiosService biosService = new BiosService();
 * List<Bios> biosList = biosService.getBios();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     BiosService biosService = new BiosService();
 *     List<Bios> biosList = biosService.getBios(session);
 * }
 * }</pre>
 */

public class BiosService {

    /**
     * Retrieves a list of BIOS entries present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return a list of {@link Bios} objects representing the system BIOS.
     *         Returns an empty list if no BIOS entries are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Bios> getBios() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BIOS_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Bios.class);
    }

    /**
     * Retrieves a list of BIOS entries present in the system using the caller's
     * {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link Bios} objects representing the system BIOS.
     *         Returns an empty list if no BIOS entries are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Bios> getBios(PowerShell powerShell) {
        PowerShellResponse response = powerShell.executeCommand(CimQuery.BIOS_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Bios.class);
    }
}
