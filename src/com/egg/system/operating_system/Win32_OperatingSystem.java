package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_OperatingSystem {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_OperatingSystem(){
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getOSList() throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		List<String> operatingSystemList = new ArrayList<>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_OperatingSystem | Select-Object Name | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				operatingSystemList.add(currentLine);
			
		br.close();
		//getting error stream
		if(operatingSystemList.isEmpty()) {
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
		//strip the property_name and keep only the property value
		for(int i=0 ; i<operatingSystemList.size(); i++) {
			operatingSystemList.set(i, operatingSystemList.get(i).substring(operatingSystemList.get(i).indexOf(":")+1).strip());
		}
		
		return operatingSystemList;
		}
	
	public static Map<String, String> getOSInfo(String OSName) throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_OperatingSystem | Where-Object {$_.Name -eq '"+OSName+"'} | Select-Object Caption, InstallDate, CSName, LastBootUpTime, LocalDateTime, Distributed, NumberOfUsers, Version, BootDevice, BuildNumber, BuildType, Manufacturer, OSArchitecture, MUILanguages, PortableOperatingSystem, Primary, RegisteredUser, SerialNumber, ServicePackMajorVersion, ServicePackMinorVersion, SystemDirectory, SystemDrive, WindowsDirectory | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new HashMap<>();
		
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
