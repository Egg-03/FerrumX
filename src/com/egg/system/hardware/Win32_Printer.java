package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Win32_Printer {
	private Win32_Printer() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDeviceIDList() throws IOException {
		List<String> deviceIDList = new ArrayList<>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Printer | Select-Object DeviceID | Format-List"};
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
	
	public static Map<String, String> getCurrentPrinter(String deviceID) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Printer | Where-Object {$_.DeviceID -eq '"+deviceID+"'} | Select-Object Name, HorizontalResolution, VerticalResolution, Capabilites, CapabilityDescriptions, Default, DriverName, Hidden, Local, Network, PortName, PrintProcessor, Shared, ShareName, SpoolEnabled, WorkOffline  | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				propertyValues.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
				
			
		br.close();
		return propertyValues;
	}
}
