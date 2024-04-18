package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_PhysicalMemory {
	private static String classname = "Win32_PhysicalMemory";
	private static String attributes = "Name, Manufacturer, Model, OtherIdentifyingInfo, PartNumber, Tag, FormFactor, BankLabel, Capacity, DataWidth, Speed, ConfiguredClockSpeed, DeviceLocator, SerialNumber";
	private Win32_PhysicalMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getTag() throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getID(classname, "Tag");
	}
	
	public static Map<String, String> getMemory(String memoryID) throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getWhere(classname, "Tag", memoryID, attributes);
	}
	
}
