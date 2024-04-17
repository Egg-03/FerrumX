package com.egg.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_SoundDevice {
	private static String classname = "Win32_SoundDevice";
	private static String attributes = "ProductName, Status, Caption, PNPDeviceID, Manufacturer";
	private Win32_SoundDevice() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getSoundDeviceID() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "DeviceID");
	}
	
	public static Map<String, String> getCurrentAudioDevice(String deviceID) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
}
