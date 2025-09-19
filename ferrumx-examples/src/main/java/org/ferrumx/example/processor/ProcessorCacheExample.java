package org.ferrumx.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.processor.ProcessorCache;
import org.ferrumx.core.service.processor.ProcessorCacheService;

import java.util.List;

@Slf4j
public class ProcessorCacheExample {

    public static void main(String[] args) {

        List<ProcessorCache> caches = new ProcessorCacheService().getProcessorCaches();
        caches.forEach(cache -> log.info("Processor Cache: \n{}", cache));

        // individual fields are accessible via getter methods
    }
}
