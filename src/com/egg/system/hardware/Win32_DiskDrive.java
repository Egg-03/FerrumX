package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_DiskDrive {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_DiskDrive() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDriveID() throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDrive | Select-Object DeviceID | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		List<String> driveID = new ArrayList<>();
		
		String value="";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				if(currentLine.contains(" : "))
					driveID.add(value=currentLine.substring(currentLine.indexOf(":")+1).strip());
				else {
					int lastIndex = driveID.size()-1;
					driveID.set(lastIndex, driveID.get(lastIndex).concat(value));
				}
				
		br.close();
		
		//getting error stream
				if(driveID.isEmpty()) {
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
		return driveID;
	}
	
	public static Map<String, String> getDrive(String driveID) throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDrive | Where-Object {$_.DeviceID -eq '"+driveID+"'} | Select-Object Caption, Model, Size, FirmwareRevision, SerialNumber, Partitions, Status, InterfaceType, PNPDeviceID | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> drives = new LinkedHashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					drives.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					drives.replace(key, value.concat(currentLine.strip()));
			}
		br.close();
		
		//getting error stream
				if(drives.isEmpty()) {
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
		return drives;
	}
}
