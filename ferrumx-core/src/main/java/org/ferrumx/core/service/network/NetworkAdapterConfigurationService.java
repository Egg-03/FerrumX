package org.ferrumx.core.service.network;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.network.NetworkAdapterConfiguration;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching network adapter configuration information from the system.
 * <p>
 * This class executes the {@link CimQuery#NETWORK_ADAPTER_CONFIGURATION_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link NetworkAdapterConfiguration} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * NetworkAdapterConfigurationService configService = new NetworkAdapterConfigurationService();
 * List<NetworkAdapterConfiguration> configs = configService.getNetworkAdapterConfiguration();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     NetworkAdapterConfigurationService configService = new NetworkAdapterConfigurationService();
 *     List<NetworkAdapterConfiguration> configs = configService.getNetworkAdapterConfiguration(session);
 * }
 * }</pre>
 */

public class NetworkAdapterConfigurationService {

    /**
     * Retrieves a list of network adapter configurations present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method concurrently.
     *
     * @return a list of {@link NetworkAdapterConfiguration} objects representing the system's network adapters.
     *         Returns an empty list if no configurations are detected.
     */
    @NotNull
    public List<NetworkAdapterConfiguration> getNetworkAdapterConfiguration() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_CONFIGURATION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapterConfiguration.class);
    }

    /**
     * Retrieves a list of network adapter configurations using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link NetworkAdapterConfiguration} objects representing the system's network adapters.
     *         Returns an empty list if no configurations are detected.
     */
    @NotNull
    public List<NetworkAdapterConfiguration> getNetworkAdapterConfiguration(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.NETWORK_ADAPTER_CONFIGURATION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapterConfiguration.class);
    }

}
