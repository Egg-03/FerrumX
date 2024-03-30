package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Relates the Drive ID provided in Win32_DiskDrive with it's associated partitions in Win32_DiskPartition
public class Win32_DiskDriveToDiskPartition {
	private Win32_DiskDriveToDiskPartition() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getPartitionList(String driveID) throws IOException{
		String command[] = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDriveToDiskPartition | Where-Object {$_.Antecedent.DeviceID -eq '"+driveID+"'} | Select-Object Dependent | Format-List"};
		
		List<String> partitionList = new ArrayList<>();
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				partitionList.add(currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\"")));
		br.close();
		return partitionList;
	}
}
