package org.ferrumx.example.memory;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.memory.PhysicalMemory;
import org.ferrumx.core.service.memory.PhysicalMemoryService;

import java.util.List;

@Slf4j
public class PhysicalMemoryExample {

    public static void main(String[] args) {
        List<PhysicalMemory> memoryList = new PhysicalMemoryService().getPhysicalMemories();
        memoryList.forEach(memory -> log.info("Physical Memory: \n{}", memory));

        // individual fields are accessible via getter methods
    }
}
