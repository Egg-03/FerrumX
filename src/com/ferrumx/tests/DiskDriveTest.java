package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.system.hardware.Win32_DiskDrive;
import com.ferrumx.system.operating_system.Win32_DiskDriveToDiskPartition;
import com.ferrumx.system.operating_system.Win32_LogicalDiskToPartition;

public class DiskDriveTest {

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		List<String> diskID = Win32_DiskDrive.getDriveID();
		List<String> diskPartition;
		Map<String, String> disk = null;

		System.out.println(diskID);

		for (String id : diskID) {
			disk = Win32_DiskDrive.getDrive(id);
			diskPartition = Win32_DiskDriveToDiskPartition.getPartitionList(id);
			for (Map.Entry<String, String> entry : disk.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

			for (String currentPartition : diskPartition) {
				System.out.println("Partition: " + currentPartition + ", Drive Letter: "
						+ Win32_LogicalDiskToPartition.getDriveLetter(currentPartition));
			}
			System.out.println();
		}
	}
}
