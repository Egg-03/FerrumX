package org.ferrumx.example.battery;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.battery.Battery;
import org.ferrumx.core.service.battery.BatteryService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display battery information
 * using {@link BatteryService}.
 * <p>
 * This class executes a CIM query to retrieve all battery entries on the system
 * and logs the JSON representation of each battery object.
 * </p>
 * Individual battery attributes can also be accessed via the getter methods
 * of the {@link Battery} class.
 */
@Slf4j
public class BatteryExample {

    public static void main(String[] args) {

        List<Battery> batteries = new BatteryService().get();
        batteries.forEach(battery -> log.info("Battery: \n{}", battery));

        // individual fields are accessible via getter methods
    }
}
