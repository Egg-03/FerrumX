package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_CacheMemory {
	private static String classname = "Win32_CacheMemory";
	private static String attributes = "DeviceID, Purpose, InstalledSize, Associativity";
	private Win32_CacheMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getCPUCache(String cacheID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", cacheID, attributes);
	}
}
