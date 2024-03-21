package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.egg.formatter.CIM.CIM;

public class Win32_Baseboard {
	private Win32_Baseboard() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static HashMap<String,String> getMotherboard() throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Baseboard | Select-Object Manufacturer, Model, Product, SerialNumber, Version | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		HashMap<String, String> mbProperty = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				mbProperty.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
				
		br.close();
		//get motherbard's plug-n-play deviceID from another class 
		mbProperty.put("PNPDeviceID", CIM.getValues("Win32_MotherBoardDevice", "PNPDeviceID"));
		
		return mbProperty;
	}
}
