package org.ferrumx.example.os;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.os.OperatingSystem;
import org.ferrumx.core.service.os.OperatingSystemService;

import java.util.List;

@Slf4j
public class OperatingSystemExample {

    public static void main(String[] args) {

        List<OperatingSystem> operatingSystems = new OperatingSystemService().getOperatingSystems();
        operatingSystems.forEach(os -> log.info("Operating System: \n{}", os));

        // individual fields are accessible via getter methods
    }
}
