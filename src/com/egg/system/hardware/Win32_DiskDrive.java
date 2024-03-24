package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Win32_DiskDrive {
	private Win32_DiskDrive() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDriveID() throws IOException{
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDrive | Select-Object DeviceID | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		List<String> driveID = new ArrayList<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				driveID.add(currentLine.substring(currentLine.indexOf(":")+1).strip());
				
		br.close();
		return driveID;
	}
	
	public static Map<String, String> getDrive(String driveID) throws IOException{
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDrive | Where-Object {$_.DeviceID -eq '"+driveID+"'} | Select-Object Size, Caption, PNPDeviceID, Model, FirmwareRevision, SerialNumber, Partitions, Status, InterfaceType | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> drives = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				drives.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
				
		br.close();
		return drives;
	}
}
