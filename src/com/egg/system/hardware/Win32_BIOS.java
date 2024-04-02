package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_BIOS {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_BIOS() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getPrimaryBIOS() throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", " Get-CimInstance -ClassName Win32_BIOS | Where-Object {$_.PrimaryBIOS -eq 'True'} |Select-Object Name, Caption, Manufacturer, ReleaseDate, SMBIOSPResent, Status, Version, CurrentLanguage, SMBIOSBIOSVersion | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				propertyValues.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
		br.close();
		
		//getting error stream
				if(propertyValues.isEmpty()) {
					BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					String errorLine;
					List<String> errorList = new ArrayList<>();
					
					while((errorLine=error.readLine())!=null)
						if(!errorLine.isBlank() || !errorLine.isEmpty())
							errorList.add(errorLine);
					
					error.close();
					ErrorLog errorLog = new ErrorLog();
					
					errorLog.log("\n"+classname+"-"+methodName+"\n"+errorList.toString()+"\n\n");
				}
		return propertyValues;
	}
}
