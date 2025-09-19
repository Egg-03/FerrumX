package org.ferrumx.example.battery;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.battery.Battery;
import org.ferrumx.core.service.battery.BatteryService;

import java.util.List;

@Slf4j
public class BatteryExample {

    public static void main(String[] args) {

        List<Battery> batteries = new BatteryService().getBatteries();
        batteries.forEach(battery -> log.info("Battery: \n{}", battery));

        // individual fields are accessible via getter methods
    }
}
