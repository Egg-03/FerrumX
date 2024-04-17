package com.egg.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_Printer {
	private static String classname = "Win32_Printer";
	private static String attributes = "Name, HorizontalResolution, VerticalResolution, Capabilities, CapabilityDescriptions, Default, DriverName, Hidden, Local, Network, PortName, PrintProcessor, Shared, ShareName, SpoolEnabled, WorkOffline";
	private Win32_Printer() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDeviceIDList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "DeviceID");
	}
	
	public static Map<String, String> getCurrentPrinter(String deviceID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
}
