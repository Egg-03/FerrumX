package com.egg.system.networking;

import java.io.IOException;

import com.egg.formatter.CIM.CIM;

//Very often there exists more than one network adapter for a single device. For this reason, I am not going to use CIM or WMIC formatter
//Because they can only read a single line
//This also means that this program cannot support detecting more than one hardware at a time. 
//For example, if there are two physical CPUs in a PC, this program will detect only one.
//I plan to modify this behavior later as network adapters and RAMs often have multiple physical hardware.
// I will implement a custom CIM formatter here that will first get the device IDs and then loop through all the device IDs and get the required adapter info
//If this succeeds, I will modify the original adapters sometime later, to incorporate this change
//which will allow detection of multiple hardware of the same class/category
public class Win32_NetworkAdapter {
	private Win32_NetworkAdapter() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getDeviceID() throws IOException {
		return CIM.getValues("Win32_NetworkAdapter", "DeviceID");
	}
}
