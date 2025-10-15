package org.ferrumx.core.service.network;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.network.NetworkAdapter;
import org.ferrumx.core.mapping.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching network adapter information from the system.
 * <p>
 * This class executes the {@link CimQuery#NETWORK_ADAPTER_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link NetworkAdapter} objects.
 * <p>
 * <h2>Thread safety</h2>
 * Methods of class are not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * NetworkAdapterService adapterService = new NetworkAdapterService();
 * List<NetworkAdapter> adapters = adapterService.getNetworkAdapters();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     NetworkAdapterService adapterService = new NetworkAdapterService();
 *     List<NetworkAdapter> adapters = adapterService.getNetworkAdapters(session);
 * }
 * }</pre>
 */

public class NetworkAdapterService {

    /**
     * Retrieves a list of network adapters present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     *
     * @return a list of {@link NetworkAdapter} objects representing the system's network adapters.
     *         Returns an empty list if no adapters are detected.
     */
    @NotNull
    public List<NetworkAdapter> getNetworkAdapters() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }

    /**
     * Retrieves a list of network adapters using the caller's {@link PowerShell} session.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link NetworkAdapter} objects representing the system's network adapters.
     *         Returns an empty list if no adapters are detected.
     */
    @NotNull
    public List<NetworkAdapter> getNetworkAdapters(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.NETWORK_ADAPTER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }
}
