package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

public class Win32_DiskDrive {
	private static String classname = "Win32_DiskDrive";
	private static String attributes = "Caption, Model, Size, FirmwareRevision, SerialNumber, Partitions, Status, InterfaceType, PNPDeviceID";
	private Win32_DiskDrive() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDriveID() throws IOException, IndexOutOfBoundsException{
			return CIM_ML.getID(classname, "DeviceID");
	}
	
	public static Map<String, String> getDrive(String driveID) throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getWhere(classname, "DeviceID", driveID, attributes);
	}
}
