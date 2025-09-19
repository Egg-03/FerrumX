package org.ferrumx.example.storage;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.storage.DiskPartition;
import org.ferrumx.core.service.storage.DiskPartitionService;

import java.util.List;

@Slf4j
public class DiskPartitionExample {

    public static void main(String[] args) {

        List<DiskPartition> diskPartitions = new DiskPartitionService().getDiskPartitions();
        diskPartitions.forEach(diskPartition -> log.info("Disk Partitions: \n{}", diskPartition));

        // individual fields are accessible via getter methods
    }
}
