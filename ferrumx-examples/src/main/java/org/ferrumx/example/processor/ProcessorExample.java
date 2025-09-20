package org.ferrumx.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.service.processor.ProcessorService;

import java.util.Optional;

/**
 * Example class demonstrating how to fetch and display processor information
 * using {@link ProcessorService}.
 * <p>
 * This class retrieves an {@link Optional} {@link Processor} object and logs its JSON
 * representation. It also shows how to access individual attributes of the processor
 * via getter methods.
 */
@Slf4j
public class ProcessorExample {

    public static void main (String[] args) {

        // usage with Optional
        Optional<Processor> optionalProcessor = new ProcessorService().getProcessor();
        optionalProcessor.ifPresent(processor -> log.info("Processor Information: \n{}", processor));

        // usage without Optional
        Processor processor = new ProcessorService().getProcessor().orElseThrow(); // will throw NoSuchElementException if not present
        log.info("Processor Information: \n{}", processor);

        // individual fields are accessible via getter methods
        log.info("Processor Name: {}", processor.getName());
        log.info("Processor Manufacturer: {}", processor.getManufacturer());
        log.info("Processor Max Clock Speed: {} MHz", processor.getMaxClockSpeed());
    }
}
