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
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getNetworkAdapterConfiguration()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * NetworkAdapterConfigurationService configService = new NetworkAdapterConfigurationService();
 * List<NetworkAdapterConfiguration> configs = configService.getNetworkAdapterConfiguration();
 * configs.forEach(System.out::println);
 * }</pre>
 */

public class NetworkAdapterConfigurationService {

    /**
     * Retrieves a non-null list of network adapter configurations present in the system.
     *
     * @return a list of {@link NetworkAdapterConfiguration} objects representing the system's network adapters.
     *         Returns an empty list if no network adapter configurations are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<NetworkAdapterConfiguration> getNetworkAdapterConfiguration() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_CONFIGURATION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapterConfiguration.class);
    }

}
