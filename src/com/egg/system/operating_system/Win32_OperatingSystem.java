package com.egg.system.operating_system;

import java.io.IOException;

import com.egg.formatter.wmic.WMIC;

public class Win32_OperatingSystem {
	private Win32_OperatingSystem(){
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
	
	public static String getWMICArchitecture() throws IOException {
		return WMIC.getValues("os", "OSArchitecture");
	}
	
	public static String getManufacturer() throws IOException{
		return WMIC.getValues("os", "Manufacturer");
	}
	
	public static String getSystemDrive() throws IOException{
		return WMIC.getValues("os", "SystemDrive");
	}
	
	public static String getWindowsDirectory() throws IOException {
		return WMIC.getValues("os", "WindowsDirectory");
	}
	
	public static String getBuildNumber() throws IOException{
		return WMIC.getValues("os", "BuildNumber");
	}
	
	public static String getInstallDate() throws IOException{
		String date = WMIC.getValues("os", "InstallDate");
		return date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6,8);
	}
}
