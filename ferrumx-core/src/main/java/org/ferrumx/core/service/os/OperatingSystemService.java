package org.ferrumx.core.service.os;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.os.OperatingSystem;
import org.ferrumx.core.mapping.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching operating system information from the system.
 * <p>
 * This class executes the {@link CimQuery#OPERATING_SYSTEM_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link OperatingSystem} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * OperatingSystemService osService = new OperatingSystemService();
 * List<OperatingSystem> operatingSystems = osService.getOperatingSystems();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     OperatingSystemService osService = new OperatingSystemService();
 *     List<OperatingSystem> operatingSystems = osService.getOperatingSystems(session);
 * }
 * }</pre>
 */

public class OperatingSystemService {

    /**
     * Retrieves a list of operating systems present on the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * Not thread-safe.
     *
     * @return a list of {@link OperatingSystem} objects representing the system's operating systems.
     *         Returns an empty list if none are detected.
     */
    @NotNull
    public List<OperatingSystem> getOperatingSystems() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.OPERATING_SYSTEM_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), OperatingSystem.class);
    }

    /**
     * Retrieves a list of operating systems using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link OperatingSystem} objects representing the system's operating systems.
     *         Returns an empty list if none are detected.
     */
    @NotNull
    public List<OperatingSystem> getOperatingSystems(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.OPERATING_SYSTEM_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), OperatingSystem.class);
    }
}
