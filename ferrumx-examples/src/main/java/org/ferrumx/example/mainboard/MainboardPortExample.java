package org.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.mainboard.MainboardPort;
import org.ferrumx.core.service.mainboard.MainboardPortService;

import java.util.List;

@Slf4j
public class MainboardPortExample {

    public static void main(String[] args) {

        List<MainboardPort> mainboardPorts = new MainboardPortService().getMainboardPorts();
        mainboardPorts.forEach(port -> log.info("Port : \n{}", port));

        // individual fields are accessible via getter methods

    }
}
