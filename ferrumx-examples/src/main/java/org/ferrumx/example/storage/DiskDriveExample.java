package org.ferrumx.example.storage;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.storage.DiskDrive;
import org.ferrumx.core.service.storage.DiskDriveService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display disk drive information
 * using {@link DiskDriveService}.
 * <p>
 * This class retrieves a list of {@link DiskDrive} objects and logs their JSON representation.
 * Individual fields of each disk drive can be accessed via the respective getter methods.
 */
@Slf4j
public class DiskDriveExample {

    public static void main(String[] args) {

        List<DiskDrive> diskDrives = new DiskDriveService().getDiskDrives();
        diskDrives.forEach(diskDrive -> log.info("Disk Drive: \n{}", diskDrive));

        // individual fields are accessible via getter methods
    }
}
