package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Win32_TimeZone {
	private Win32_TimeZone() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getOSTimeZone() throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_TimeZone | Select-Object Caption, Bias, StandardName | Format-List"};
		
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
