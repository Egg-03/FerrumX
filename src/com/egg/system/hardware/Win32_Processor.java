package com.egg.system.hardware;

import java.io.IOException;

import com.egg.formatter.wmic.WMIC;

public class Win32_Processor{
	
	private Win32_Processor() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getName() throws IOException {
		return WMIC.getValues("cpu", "Name");
	}
	
	public static String getCores() throws IOException {
		return WMIC.getValues("cpu", "NumberOfCores");
	}
	
	public static String getThreads() throws IOException {
		return WMIC.getValues("cpu", "ThreadCount");
	}
	
	public static String getManufacturer() throws IOException{
		return WMIC.getValues("cpu", "Manufacturer");
	}
	
	public static String getAddressWidth() throws IOException{
		return WMIC.getValues("cpu", "AddressWidth");
	}
	
	public static String getL2CacheSize() throws IOException{
		return WMIC.getValues("cpu", "L2CacheSize");
	}
	
	public static String getL3CacheSize() throws IOException{
		return WMIC.getValues("cpu", "L3CacheSize");
	}
	
	public static String getCurrentClockSpeed() throws IOException{
		return WMIC.getValues("cpu", "CurrentClockSpeed");
	}
	
	public static String getMaxClockSpeed() throws IOException{
		return WMIC.getValues("cpu", "MaxClockSpeed");
	}
	
	public static String getBusSpeed() throws IOException{
		return WMIC.getValues("cpu", "ExtClock");
	}
	
	public static String getSocket() throws IOException{
		return WMIC.getValues("cpu", "SocketDesignation");
	}
}
