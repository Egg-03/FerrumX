package io.github.eggy03.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.mainboard.Bios;
import io.github.eggy03.ferrumx.core.service.mainboard.BiosService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display BIOS information
 * using {@link BiosService}.
 * <p>
 * This class executes a CIM query to retrieve BIOS details of the system
 * and logs the JSON representation of each {@link Bios} object.
 * </p>
 * Individual attributes of each BIOS entry can be accessed via
 * the getter methods of the {@link Bios} class.
 */
@Slf4j
public class BiosExample {

    public static void main(String[] args) {

        List<Bios> biosList = new BiosService().get();
        biosList.forEach(bios -> log.info("Bios : \n{}", bios));

        // individual fields are accessible via getter methods

    }
}
