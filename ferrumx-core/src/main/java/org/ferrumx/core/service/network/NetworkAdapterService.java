package org.ferrumx.core.service.network;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.network.NetworkAdapter;
import org.ferrumx.core.util.MapperUtil;
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
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getNetworkAdapters()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * NetworkAdapterService adapterService = new NetworkAdapterService();
 * List<NetworkAdapter> adapters = adapterService.getNetworkAdapters();
 * adapters.forEach(System.out::println);
 * }</pre>
 */

public class NetworkAdapterService {

    /**
     * Retrieves a non-null list of network adapters present in the system.
     *
     * @return a list of {@link NetworkAdapter} objects representing the system's network adapters.
     *         Returns an empty list if no network adapters are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<NetworkAdapter> getNetworkAdapters() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.NETWORK_ADAPTER_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), NetworkAdapter.class);
    }
}
