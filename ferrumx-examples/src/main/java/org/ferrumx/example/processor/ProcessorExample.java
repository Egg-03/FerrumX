package org.ferrumx.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.service.processor.ProcessorService;

import java.util.List;
import java.util.Optional;

/**
 * Example class demonstrating how to fetch and display processor information
 * using {@link ProcessorService}.
 * <p>
 * This class retrieves an {@link Optional} {@link Processor} object and logs its JSON
 * representation. It also shows how to access individual attributes of the processor
 * via getter methods.
 * </p>
 */
@Slf4j
public class ProcessorExample {

    public static void main (String[] args) {

        List<Processor> processorList = new ProcessorService().get();
        processorList.forEach(processor -> log.info("Processor: \n{}", processor.toString()));

        // individual fields are accessible via getter methods
        log.info("Processor Name: {}", processorList.getFirst().getName());
        log.info("Processor Manufacturer: {}", processorList.getFirst().getManufacturer());
        log.info("Processor Max Clock Speed: {} MHz", processorList.getFirst().getMaxClockSpeed());
    }
}
