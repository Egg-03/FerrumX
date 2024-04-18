package com.ferrumx.system.operating_system;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_TimeZone {
	private static String classname = "Win32_TimeZone";
	private static String attributes = "Caption, Bias, StandardName";
	private Win32_TimeZone() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getOSTimeZone() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.get(classname, attributes);
	}
}
