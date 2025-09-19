package org.ferrumx.example.network;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.network.NetworkAdapterConfiguration;
import org.ferrumx.core.service.network.NetworkAdapterConfigurationService;

import java.util.List;

@Slf4j
public class NetworkAdapterConfigExample {

    public static void main(String[] args) {

        List<NetworkAdapterConfiguration> configurationList = new NetworkAdapterConfigurationService().getNetworkAdapterConfiguration();
        configurationList.forEach(config -> log.info("Network Adapter Configuration: \n{}", config));

        // individual fields are accessible via getter methods
    }
}
