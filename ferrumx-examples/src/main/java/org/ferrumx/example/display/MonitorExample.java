package org.ferrumx.example.display;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.display.Monitor;
import org.ferrumx.core.service.display.MonitorService;

import java.util.List;

@Slf4j
public class MonitorExample {

    public static void main(String[] args) {

        List<Monitor> monitors = new MonitorService().getMonitors();
        monitors.forEach(monitor -> log.info("Monitor: \n{}", monitor));

        // individual fields are accessible via getter methods
    }
}
