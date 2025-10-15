package io.github.eggy03.ferrumx.example.display;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.display.Monitor;
import io.github.eggy03.ferrumx.core.service.display.MonitorService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display monitor information
 * using {@link MonitorService}.
 * <p>
 * This class executes a CIM query to retrieve all monitors on the system
 * and logs the JSON representation of each monitor object.
 * </p>
 * Individual monitor attributes can also be accessed via the getter methods
 * of the {@link Monitor} class.
 */
@Slf4j
public class MonitorExample {

    public static void main(String[] args) {

        List<Monitor> monitors = new MonitorService().get();
        monitors.forEach(monitor -> log.info("Monitor: \n{}", monitor));

        // individual fields are accessible via getter methods
    }
}
