package org.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.mainboard.MainboardPort;
import org.ferrumx.core.service.mainboard.MainboardPortService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display mainboard port information
 * using {@link MainboardPortService}.
 * <p>
 * This class fetches a list of {@link MainboardPort} objects and logs their
 * JSON representation.
 * </p>
 * Individual attributes of each {@link MainboardPort} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class MainboardPortExample {

    public static void main(String[] args) {

        List<MainboardPort> mainboardPorts = new MainboardPortService().get();
        mainboardPorts.forEach(port -> log.info("Port : \n{}", port));

        // individual fields are accessible via getter methods

    }
}
