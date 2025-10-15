package org.ferrumx.example.os;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.os.OperatingSystem;
import org.ferrumx.core.service.os.OperatingSystemService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display operating system information
 * using {@link OperatingSystemService}.
 * <p>
 * This class retrieves a list of {@link OperatingSystem} objects and logs their JSON
 * representation.
 * </p>
 * Individual attributes of each {@link OperatingSystem} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class OperatingSystemExample {

    public static void main(String[] args) {

        List<OperatingSystem> operatingSystems = new OperatingSystemService().get();
        operatingSystems.forEach(os -> log.info("Operating System: \n{}", os));

        // individual fields are accessible via getter methods
    }
}
