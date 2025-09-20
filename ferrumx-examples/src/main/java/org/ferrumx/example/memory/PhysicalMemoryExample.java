package org.ferrumx.example.memory;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.memory.PhysicalMemory;
import org.ferrumx.core.service.memory.PhysicalMemoryService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display physical memory information
 * using {@link PhysicalMemoryService}.
 * <p>
 * This class fetches a list of {@link PhysicalMemory} objects and logs their
 * JSON representation.
 * <p>
 * Individual attributes of each {@link PhysicalMemory} object can be accessed
 * via their getter methods.
 */
@Slf4j
public class PhysicalMemoryExample {

    public static void main(String[] args) {
        List<PhysicalMemory> memoryList = new PhysicalMemoryService().getPhysicalMemories();
        memoryList.forEach(memory -> log.info("Physical Memory: \n{}", memory));

        // individual fields are accessible via getter methods
    }
}
