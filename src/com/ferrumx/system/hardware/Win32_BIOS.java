package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_BIOS {
	private static String classname = "Win32_BIOS";
	private static String attributes = "Name, Caption, Manufacturer, ReleaseDate, SMBIOSPResent, Status, Version, CurrentLanguage, SMBIOSBIOSVersion";
	private Win32_BIOS() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getPrimaryBIOS() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "PrimaryBIOS", "True", attributes);
	}
}
