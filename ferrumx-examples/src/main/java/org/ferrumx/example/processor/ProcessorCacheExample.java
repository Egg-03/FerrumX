package org.ferrumx.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.processor.ProcessorCache;
import org.ferrumx.core.service.processor.ProcessorCacheService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display processor cache information
 * using {@link ProcessorCacheService}.
 * <p>
 * This class retrieves a list of {@link ProcessorCache} objects and logs their JSON
 * representation.
 * </p>
 * Individual attributes of each {@link ProcessorCache} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class ProcessorCacheExample {

    public static void main(String[] args) {

        List<ProcessorCache> caches = new ProcessorCacheService().get();
        caches.forEach(cache -> log.info("Processor Cache: \n{}", cache));

        // individual fields are accessible via getter methods
    }
}
