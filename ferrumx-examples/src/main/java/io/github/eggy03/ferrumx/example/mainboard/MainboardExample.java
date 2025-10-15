package io.github.eggy03.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.mainboard.Mainboard;
import io.github.eggy03.ferrumx.core.service.mainboard.MainboardService;

import java.util.Optional;

/**
 * Example class demonstrating how to fetch and display mainboard information
 * using {@link MainboardService}.
 * <p>
 * This class shows both approaches for handling the {@link Optional} result:
 * </p>
 * <ul>
 *     <li>Using {@link Optional#ifPresent} to safely access the value.</li>
 *     <li>Using {@link Optional#orElseThrow} to directly get the value or throw
 *         a {@link java.util.NoSuchElementException} if not present.</li>
 * </ul>
 * The JSON representation of the {@link Mainboard} object is logged,
 * and individual attributes can be accessed via the getter methods.
 */
@Slf4j
public class MainboardExample {

    public static void main(String[] args) {

        // with Optional
        Optional<Mainboard> optionalMainboard = new MainboardService().get();
        optionalMainboard.ifPresent(mainboard -> log.info("Mainboard: \n{}", mainboard));

        // without Optional
        Mainboard mainboard = new MainboardService().get().orElseThrow(); // will throw NoSuchElementException if not present
        log.info("Mainboard: \n{}", mainboard);

        // individual fields are accessible via getter methods

    }
}
