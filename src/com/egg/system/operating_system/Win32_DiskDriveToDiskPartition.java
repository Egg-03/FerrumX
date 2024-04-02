package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

//Relates the Drive ID provided in Win32_DiskDrive with it's associated partitions in Win32_DiskPartition
public class Win32_DiskDriveToDiskPartition {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_DiskDriveToDiskPartition() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getPartitionList(String driveID) throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String command[] = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_DiskDriveToDiskPartition | Where-Object {$_.Antecedent.DeviceID -eq '"+driveID+"'} | Select-Object Dependent | Format-List"};
		
		List<String> partitionList = new ArrayList<>();
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				partitionList.add(currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\"")));
		br.close();
		
		//getting error stream
		if(partitionList.isEmpty()) {
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
		return partitionList;
	}
}
