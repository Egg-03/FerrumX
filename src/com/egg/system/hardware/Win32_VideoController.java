package com.egg.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_VideoController {
	private static String classname = "Win32_VideoController";
	private static String attributes = "Name, PNPDeviceID, CurrentBitsPerPixel, CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate, MaxRefreshRate, MinRefreshRate, AdapterDACType, AdapterRAM, DriverDate, DriverVersion, VideoProcessor";
	private Win32_VideoController() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getGPUID() throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getID(classname, "DeviceID");
	}
	
	public static Map<String, String> getGPU(String gpuID) throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getWhere(classname, "DeviceID", gpuID, attributes);
	}
}