package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Win32_SystemDriver {
	private Win32_SystemDriver() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDrivers() throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_SystemDriver | Select-Object State, Started, Caption, PathName | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		List<String> propertyValues = new ArrayList<>();
		
		while((currentLine=br.readLine())!=null)
				propertyValues.add(currentLine);
				
		br.close();
		return propertyValues;
	}
}
	
