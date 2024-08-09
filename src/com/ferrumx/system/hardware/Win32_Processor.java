package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_Processor class of WMI and represents the CPU
 * details of your system.
 * <p>
 * Fetches the following properties: Name, NumberOfCores, ThreadCount,
 * NumberOfLogicalProcessor, Manufacturer, AddressWidth, L2CacheSize,
 * L3CacheSize, MaxClockSpeed, ExtClock, SocketDesignation, Version, Caption,
 * Family, Stepping, VirtualizationFirmwareEnabled, ProcessorID
 *
 * @author Egg-03
 * @version 1.2.0
 */
public class Win32_Processor {
	private static String classname = "Win32_Processor";
	private static String attributes = "Name, NumberOfCores, ThreadCount, NumberOfLogicalProcessors, Manufacturer, AddressWidth, L2CacheSize, L3CacheSize, MaxClockSpeed, ExtClock, SocketDesignation, Version, Caption, Family, Stepping, VirtualizationFirmwareEnabled, ProcessorID";

	private Win32_Processor() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method fetches a list of CPUs present in the system based on their IDs
	 *
	 * @return a {@link java.util.List} of CPU IDs
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */
	public static List<String> getProcessorList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "DeviceID");
	}

	/**
	 * This method fetches the CPU properties based on the given CPU ID
	 *
	 * @param deviceID fetched from {@link Win32_Processor#getProcessorList()}
	 * @return a {@link java.util.Map} of CPU properties and their values mentioned
	 *         in the class description
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */
	public static Map<String, String> getCurrentProcessor(String deviceID)
			throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
}
