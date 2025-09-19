package org.ferrumx.example.storage;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.storage.DiskDrive;
import org.ferrumx.core.service.storage.DiskDriveService;

import java.util.List;

@Slf4j
public class DiskDriveExample {

    public static void main(String[] args) {

        List<DiskDrive> diskDrives = new DiskDriveService().getDiskDrives();
        diskDrives.forEach(diskDrive -> log.info("Disk Drive: \n{}", diskDrive));

        // individual fields are accessible via getter methods
    }
}
