package io.github.eggy03.ferrumx.example.network;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.network.NetworkAdapterConfiguration;
import io.github.eggy03.ferrumx.core.service.network.NetworkAdapterConfigurationService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display network adapter configuration
 * information using {@link NetworkAdapterConfigurationService}.
 * <p>
 * This class retrieves a list of {@link NetworkAdapterConfiguration} objects and logs
 * their JSON representation.
 * </p>
 * Individual attributes of each {@link NetworkAdapterConfiguration} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class NetworkAdapterConfigExample {

    public static void main(String[] args) {

        List<NetworkAdapterConfiguration> configurationList = new NetworkAdapterConfigurationService().get();
        configurationList.forEach(config -> log.info("Network Adapter Configuration: \n{}", config));

        // individual fields are accessible via getter methods
    }
}
