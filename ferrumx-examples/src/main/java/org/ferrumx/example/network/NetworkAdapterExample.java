package org.ferrumx.example.network;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.network.NetworkAdapter;
import org.ferrumx.core.service.network.NetworkAdapterService;

import java.util.List;

@Slf4j
public class NetworkAdapterExample {

    public static void main(String[] args) {

        List<NetworkAdapter> networkAdapters = new NetworkAdapterService().getNetworkAdapters();
        networkAdapters.forEach(adapter -> log.info("Network Adapter: \n{}", adapter));

        // individual fields are accessible via getter methods
    }
}
