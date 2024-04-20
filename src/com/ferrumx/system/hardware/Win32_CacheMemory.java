package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class contains methods that query the Win32_CacheMemory class of WMI
 * and fetches the following properties and their values: DeviceID, Purpose, InstalledSize, Associativity
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_CacheMemory {
	private static String classname = "Win32_CacheMemory";
	private static String attributes = "DeviceID, Purpose, InstalledSize, Associativity";
	private Win32_CacheMemory() {
		throw new IllegalStateException("Utility Class");
	}
	/**
	 * This method returns the Cache Memory details of a CPU
	 * @param cacheID this ID is from the list of cacheIDs produced by {@link Win32_AssociatedProcessorMemory#getCacheID(String)} which in turn retrieves those IDs by taking in the CPU DeviceID from {@link Win32_Processor#getProcessorList()}
	 * @return a particular level of cache memory details of a particular CPU at a time
	 * @throws IOException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there are I/O Errors during streaming of data from and to Powershell and other generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there is a parsing error of data fetched from Windows Powershell
	 */
	public static Map<String, String> getCPUCache(String cacheID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", cacheID, attributes);
	}
}
