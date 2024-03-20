package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Win32_BIOS {
	
	private Win32_BIOS() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static HashMap<String, String> getPrimaryBIOS() throws IOException {
		String[] command = {"powershell.exe", "/c", " Get-CimInstance -ClassName Win32_BIOS | Where-Object {$_.PrimaryBIOS -eq 'True'} |Select-Object Name, Caption, SMBIOSPResent, Status, Version, CurrentLanguage, SMBIOSBIOSVersion, Manufacturer, ReleaseDate | Format-List"};
		
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
