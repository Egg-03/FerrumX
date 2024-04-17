package com.egg.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;
import com.egg.formatter.cim.CIM_SL;

public class Win32_Baseboard {
	private static String classname = "Win32_Baseboard";
	private static String attributes = "Manufacturer, Model, Product, SerialNumber, Version";
	private Win32_Baseboard() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String,String> getMotherboard() throws IOException, IndexOutOfBoundsException {
		
		Map<String, String> mbProperty = CIM_ML.get(classname, attributes);
		
		//get motherbard's plug-n-play deviceID from another class 
		mbProperty.put("PNPDeviceID", CIM_SL.get("Win32_MotherBoardDevice", "PNPDeviceID"));
		
		return mbProperty;
	}
}
