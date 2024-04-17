package com.egg.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_NetworkAdapter {
	private static String classname = "Win32_NetworkAdapter";
	private static String attributes = "Name, Description, PNPDeviceID, MACAddress, Installed, NetEnabled, NetConnectionID, PhysicalAdapter, TimeOfLastReset";
	private Win32_NetworkAdapter() {
		throw new IllegalStateException("Utility Class");
	}
	
	//will retrieve all the adapter IDs which are currently active and providing Internet
	public static List<String> getDeviceIDList() throws IOException, IndexOutOfBoundsException {
			return CIM_ML.getIDWhere(classname, "NetEnabled", "True", "DeviceID");
		}
	
	//will return a hashmap of the following properties as a key and their corresponding values:
	//Name, Description, PNPDeviceID, MACAddress, Installed, NetEnabled, NetConnectionID, PhysicalAdapter, TimeOfLastReset
	public static Map<String, String> getNetworkAdapters(String deviceID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
	
}
