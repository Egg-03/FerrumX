package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class contains methods that query the Win32_BIOS class of WMI
 * and fetches the following properties and their values: Name, Caption, Manufacturer, ReleaseDate, SMBIOSPResent, Status, Version, CurrentLanguage, SMBIOSBIOSVersion
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_BIOS {
	private static String classname = "Win32_BIOS";
	private static String attributes = "Name, Caption, Manufacturer, ReleaseDate, SMBIOSPResent, Status, Version, CurrentLanguage, SMBIOSBIOSVersion";
	private Win32_BIOS() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * Fetches Primary BIOS Information (If your motherboard has multiple BIOSes, the Main BIOS information will always be fetched). 
	 * Internally calls {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 * with the parameters being (classname, "PrimaryBIOS", "True", attributes);
	 * @return a {@link java.util.Map} containing the properties and their values mentioned in the class description, as key-value pairs
	 * @throws IOException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there are I/O Errors during streaming of data from and to Powershell and other generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there is a parsing error of data fetched from Windows Powershell
	 */
	public static Map<String, String> getPrimaryBIOS() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "PrimaryBIOS", "True", attributes);
	}
}
