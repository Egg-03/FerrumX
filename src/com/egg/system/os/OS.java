package com.egg.system.os;

import java.io.IOException;

import com.egg.wmic.formatter.WMIC;

public class OS {
	private OS(){
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getName() {
		return System.getProperty("os.name");
	}
	
	public static String getVersion() {
		return System.getProperty("os.version");
	}
	
	public static String getArchitecture() {
		return System.getProperty("os.arch");
	}
	
	public static String getWMICName() throws IOException {
		return WMIC.getValues("os", "Caption");
	}
}
