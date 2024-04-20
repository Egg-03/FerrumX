package com.ferrumx.system.operating_system;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * Fetches the OS Time-Zone information by querying WIn32_TimeZone class of WMI
 * The following attributes are fetched: Caption, Bias, StandardName
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_TimeZone {
	private static String classname = "Win32_TimeZone";
	private static String attributes = "Caption, Bias, StandardName";
	private Win32_TimeZone() {
		throw new IllegalStateException("Utility Class");
	}
	/**
	 * Retrieves the OS Time-zone
	 * @return a {@link java.util.Map} of Time-zone properties and values mentioned in the class description
	 * @throws IOException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} when there are I/O Errors during streaming of data from and to Powershell and other generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} when there is a parsing error of data fetched from Windows Powershell
	 */
	public static Map<String, String> getOSTimeZone() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.get(classname, attributes);
	}
}
