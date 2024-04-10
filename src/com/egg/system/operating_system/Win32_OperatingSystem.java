package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_OperatingSystem {
	private static String classname = "Win32_OperatingSystem";
	private Win32_OperatingSystem(){
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getOSList() throws IOException, IndexOutOfBoundsException {
		String methodName = "getOSList()";
		List<String> operatingSystemList = new ArrayList<>();
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_OperatingSystem | Select-Object Name | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		try {
			int exitCode = process.waitFor();
			if(exitCode!=0) {
				BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String errorLine;
     			List<String> errorList = new ArrayList<>();
				
				while((errorLine=error.readLine())!=null)
					if(!errorLine.isBlank() || !errorLine.isEmpty())
						errorList.add(errorLine);
				
				error.close();
				ErrorLog errorLog = new ErrorLog();
				
				errorLog.log("\n"+classname+"-"+methodName+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n");
			}
		}catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n"+classname+"-"+methodName+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
			
		String value = "";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					operatingSystemList.add(value =currentLine);
				else {
					int lastIndex = operatingSystemList.size()-1;
					operatingSystemList.set(lastIndex, operatingSystemList.get(lastIndex).concat(value));
				}
			}
				
			
		br.close();
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<operatingSystemList.size(); i++) {
			operatingSystemList.set(i, operatingSystemList.get(i).substring(operatingSystemList.get(i).indexOf(":")+1).strip());
		}
		
		return operatingSystemList;
		}
	
	public static Map<String, String> getOSInfo(String OSName) throws IOException, IndexOutOfBoundsException {
		String methodName = "getOSInfo(String OSName)";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_OperatingSystem | Where-Object {$_.Name -eq '"+OSName+"'} | Select-Object Caption, InstallDate, CSName, LastBootUpTime, LocalDateTime, Distributed, NumberOfUsers, Version, BootDevice, BuildNumber, BuildType, Manufacturer, OSArchitecture, MUILanguages, PortableOperatingSystem, Primary, RegisteredUser, SerialNumber, ServicePackMajorVersion, ServicePackMinorVersion, SystemDirectory, SystemDrive, WindowsDirectory | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		try {
			int exitCode = process.waitFor();
			if(exitCode!=0) {
				BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String errorLine;
     			List<String> errorList = new ArrayList<>();
				
				while((errorLine=error.readLine())!=null)
					if(!errorLine.isBlank() || !errorLine.isEmpty())
						errorList.add(errorLine);
				
				error.close();
				ErrorLog errorLog = new ErrorLog();
				
				errorLog.log("\n"+classname+"-"+methodName+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n");
			}
		}catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n"+classname+"-"+methodName+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					propertyValues.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					propertyValues.replace(key, value.concat(currentLine.strip()));
			}
		br.close();
		return propertyValues;
	}
}
