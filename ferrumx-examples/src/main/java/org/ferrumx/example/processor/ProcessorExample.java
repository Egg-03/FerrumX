package org.ferrumx.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.service.processor.ProcessorService;

import java.util.Optional;

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
