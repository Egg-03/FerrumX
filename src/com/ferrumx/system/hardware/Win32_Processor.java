package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_Processor{
	private static String classname = "Win32_Processor";
	private static String attributes = "Name, NumberOfCores, ThreadCount, Manufacturer, AddressWidth, L2CacheSize, L3CacheSize, MaxClockSpeed, ExtClock, SocketDesignation, Version, Caption, Family, Stepping, VirtualizationFirmwareEnabled, ProcessorID";
	private Win32_Processor() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getProcessorList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "DeviceID");
	}
	
	public static Map<String, String> getCurrentProcessor(String deviceID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
}
