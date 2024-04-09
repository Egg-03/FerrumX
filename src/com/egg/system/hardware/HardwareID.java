package com.egg.system.hardware;

import java.io.IOException;

import com.egg.formatter.cim.CIM;
import com.egg.system.currentuser.User;

public class HardwareID {
	private HardwareID() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getHardwareID() throws IOException {
		String cpuName = CIM.getValues("Win32_Processor", "Name");
		String cpuId = CIM.getValues("Win32_Processor", "ProcessorID");
		String motherBoardName = CIM.getValues("Win32_BaseBoard", "Product");
		String deviceName = CIM.getValues("Win32_OperatingSystem", "CSName");
		String userName = User.getUsername();
		int countRAM = Win32_PhysicalMemory.getTag().size();
		int countStorage = Win32_DiskDrive.getDriveID().size();
		
		return userName+"/"+deviceName+"/"+cpuName+"/"+cpuId+"/"+motherBoardName+"/"+countRAM+"/"+countStorage;
	}
}
