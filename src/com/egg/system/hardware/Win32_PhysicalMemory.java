package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_PhysicalMemory {
	private static String classname = "Win32_PhysicalMemory";
	private Win32_PhysicalMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getTag() throws IOException, IndexOutOfBoundsException{
		String methodName = "getTag()";
		List<String> memoryTag = new ArrayList<>();
		
		String[] tagCommand = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PhysicalMemory | Select-Object Tag | Format-List"};
		
		Process tagProcess = Runtime.getRuntime().exec(tagCommand);
		try {
			int exitCode = tagProcess.waitFor();
			if(exitCode!=0) {
				BufferedReader error = new BufferedReader(new InputStreamReader(tagProcess.getErrorStream()));
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
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(tagProcess.getInputStream()));
		String currentTagLine;
		
		String tagValue = "";
		while((currentTagLine=br.readLine())!=null)
			if(!currentTagLine.isBlank() || !currentTagLine.isEmpty()) {
				if(currentTagLine.contains(" : "))
					memoryTag.add(tagValue= currentTagLine);
				else {
					int lastIndex = memoryTag.size()-1;
					memoryTag.set(lastIndex, memoryTag.get(lastIndex).concat(tagValue));
				}
			}
		//strip the property_name and keep only the property value
		for(int i=0 ; i<memoryTag.size(); i++) {
			memoryTag.set(i, memoryTag.get(i).substring(memoryTag.get(i).indexOf(":")+1).strip());
		}
		
		br.close();
		return memoryTag;
	}
	
	public static Map<String, String> getMemory(String memoryID) throws IOException, IndexOutOfBoundsException{
		String methodName = "getMemory(String memoryID)";
		Map<String, String> memory = new LinkedHashMap<>();
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PhysicalMemory | Where-Object {$_.Tag -eq '"+memoryID+"'} | Select-Object Name, Manufacturer, Model, OtherIdentifyingInfo, PartNumber, Tag, FormFactor, BankLabel, Capacity, DataWidth, Speed, ConfiguredClockSpeed, DeviceLocator, SerialNumber | Format-List"};
		
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
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					memory.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					memory.replace(key, value.concat(currentLine.strip()));
			}		
		br.close();
		
		return memory;
	}
	
}
