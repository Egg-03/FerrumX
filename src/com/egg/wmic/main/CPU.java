package com.egg.wmic.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CPU{
	private static Process cpu;
	
	protected static String getValues(String WMIC_Attribute) throws IOException {
		String[] command = {"cmd", "/c", "wmic cpu get "+WMIC_Attribute+" /format:list"};
		cpu = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(cpu.getInputStream()));
		
		String currentLine;
		String actualName = null;
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				actualName = currentLine;
		
		br.close();
		return actualName.substring(actualName.indexOf("=")+1).strip();
	}
	
//	public static String getName() throws IOException {
//		return getValues("Name");
//	}
//	
//	public static String getCores() throws IOException {
//		return getValues("NumberOfCores");
//	}
//	
//	public static String getThreads() throws IOException {
//		return getValues("ThreadCount");
//	}
//	
//	public static String getManufacturer() throws IOException{
//		return getValues("Manufacturer");
//	}
//	
//	public static String getAddressWidth() throws IOException{
//		return getValues("AddressWidth");
//	}
//	
//	public static String getL2CacheSize() throws IOException{
//		return getValues("L2CacheSize");
//	}
//	
//	public static String getL3CacheSize() throws IOException{
//		return getValues("L3CacheSize");
//	}
//	
//	public static String getCurrentClockSpeed() throws IOException{
//		return getValues("CurrentClockSpeed");
//	}
//	
//	public static String getMaxClockSpeed() throws IOException{
//		return getValues("MaxClockSpeed");
//	}
//	
//	public static String getBusSpeed() throws IOException{
//		return getValues("ExtClock");
//	}
//	
//	public static String getSocket() throws IOException{
//		return getValues("SocketDesignation");
//	}
}
