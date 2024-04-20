package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;

/** This class contains methods that query Win32_Baseboard and Win32_MotherboardDevice classes of WMI
 * and fetches the following properties: Manufacturer, Model, Product, SerialNumber, Version
 * @author Egg-03
 * @version 1.1.0*/
public class Win32_Baseboard {
	private static String classname = "Win32_Baseboard";
	private static String attributes = "Manufacturer, Model, Product, SerialNumber, Version";
	private Win32_Baseboard() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * This method calls the {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} function and passes the WMI Classname and the properties whose values we want to fetch, as parameters
	 * @return the Manufacturer, Model, Product, SerialNumber, Version details of your Motherboard in the form of a {@link java.util.Map}
	 * @throws IOException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} when there are I/O Errors during streaming of data from and to Powershell and other generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} when there is a parsing error of data fetched from Windows Powershell
	 */
	public static Map<String,String> getMotherboard() throws IOException, IndexOutOfBoundsException {
		
		Map<String, String> mbProperty = CIM_ML.get(classname, attributes);
		
		//get motherbard's plug-n-play deviceID from another class 
		mbProperty.put("PNPDeviceID", CIM_SL.get("Win32_MotherBoardDevice", "PNPDeviceID"));
		
		return mbProperty;
	}
}
