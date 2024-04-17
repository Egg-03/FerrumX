package com.egg.system.operating_system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_OperatingSystem {
	private static String classname = "Win32_OperatingSystem";
	private static String attributes = "Caption, InstallDate, CSName, LastBootUpTime, LocalDateTime, Distributed, NumberOfUsers, Version, BootDevice, BuildNumber, BuildType, Manufacturer, OSArchitecture, MUILanguages, PortableOperatingSystem, Primary, RegisteredUser, SerialNumber, ServicePackMajorVersion, ServicePackMinorVersion, SystemDirectory, SystemDrive, WindowsDirectory";
	private Win32_OperatingSystem(){
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getOSList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "Name");
	}
	
	public static Map<String, String> getOSInfo(String OSName) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "Name", OSName, attributes);
	}
}
