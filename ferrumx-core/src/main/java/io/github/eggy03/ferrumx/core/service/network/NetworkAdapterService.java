package io.github.eggy03.ferrumx.core.service.network;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.core.constant.CimQuery;
import io.github.eggy03.ferrumx.core.entity.network.NetworkAdapter;
import io.github.eggy03.ferrumx.core.mapping.MapperUtil;
import io.github.eggy03.ferrumx.core.service.CommonServiceInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching network adapter information from the system.
 * <p>
 * This class executes the {@link CimQuery#NETWORK_ADAPTER_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link NetworkAdapter} objects.
 * </p>
 *
 * <h2>Thread safety</h2>
 * Methods of class are not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * NetworkAdapterService adapterService = new NetworkAdapterService();
 * List<NetworkAdapter> adapters = adapterService.get();
 *
 * // API with re-usable session (caller manages session lifecycle)
 * try (PowerShell session = PowerShell.openSession()) {
 *     NetworkAdapterService adapterService = new NetworkAdapterService();
 *     List<NetworkAdapter> adapters = adapterService.get(session);
 * }
 * }</pre>
 * @since 2.0.0
 * @author Egg-03
 */

public class NetworkAdapterService implements CommonServiceInterface<NetworkAdapter> {

    /**
     * Retrieves a list of network adapters present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * </p>
     *
     * @return a list of {@link NetworkAdapter} objects representing the system's network adapters.
     *         Returns an empty list if no adapters are detected.
     */
    @NotNull
    @Override
    public List<NetworkAdapter> get() {

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
    @Override
    public List<NetworkAdapter> get(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.NETWORK_ADAPTER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }
}
