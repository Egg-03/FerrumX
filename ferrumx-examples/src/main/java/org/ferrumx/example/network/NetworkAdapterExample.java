package org.ferrumx.example.network;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.network.NetworkAdapter;
import org.ferrumx.core.service.network.NetworkAdapterService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display network adapter information
 * using {@link NetworkAdapterService}.
 * <p>
 * This class retrieves a list of {@link NetworkAdapter} objects and logs their JSON
 * representation.
 * <p>
 * Individual attributes of each {@link NetworkAdapter} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class NetworkAdapterExample {

    public static void main(String[] args) {

        List<NetworkAdapter> networkAdapters = new NetworkAdapterService().getNetworkAdapters();
        networkAdapters.forEach(adapter -> log.info("Network Adapter: \n{}", adapter));

        // individual fields are accessible via getter methods
    }
}
