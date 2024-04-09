package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

//Associates a drive letter to a given partition
public class Win32_LogicalDiskToPartition {
	private static String classname = "Win32_LogicalDiskToPartition";
	private Win32_LogicalDiskToPartition() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getDriveLetter(String partitionID) throws IOException{
		String methodName = "getDriveLetter(String partitionID)";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_LogicalDiskToPartition | Where-Object {$_.Antecedent.DeviceID -eq '"+partitionID+"'} | Select-Object Dependent | Format-List"};
		
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
		String driveLetter = "N/A";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				driveLetter = currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\""));
		br.close();
		
		return driveLetter;
	}
}
