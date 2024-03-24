package com.egg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.system.hardware.Win32_DiskDrive;

public class DiskDriveTest {

	public static void main(String[] args) throws IOException {
		List<String> diskID = Win32_DiskDrive.getDriveID();
		Map<String, String> disk = null;
		
		System.out.println(diskID);
		
		for(String id:diskID) {
			disk = Win32_DiskDrive.getDrive(id);
			for(Map.Entry<String, String> entry: disk.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println();
		}
	}
}
