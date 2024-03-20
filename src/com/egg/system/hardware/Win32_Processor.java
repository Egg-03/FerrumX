package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Win32_Processor{
	
	private Win32_Processor() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static ArrayList<String> getDeviceIDList() throws IOException {
		ArrayList<String> deviceIDList = new ArrayList<String>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Processor | Select-Object DeviceID | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				deviceIDList.add(currentLine);
			
		br.close();
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<deviceIDList.size(); i++) {
			deviceIDList.set(i, deviceIDList.get(i).substring(deviceIDList.get(i).indexOf(":")+1).strip());
		}
		
		return deviceIDList;
		}
	
	public static HashMap<String, String> getCurrentProcessor(String deviceID) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Processor | Where-Object {$_.DeviceID -eq '"+deviceID+"'} | Select-Object Name, NumberOfCores, ThreadCount, Manufacturer, AddressWidth, L2CacheSize, L3CacheSize, MaxClockSpeed, ExtClock, SocketDesignation, Version, Caption, Family, Stepping, VirtualizationFirmwareEnabled, ProcessorID | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		HashMap<String, String> propertyValues = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				propertyValues.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
				
			
		br.close();
		return propertyValues;
	}
}