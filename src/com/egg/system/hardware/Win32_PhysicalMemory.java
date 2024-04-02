package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_PhysicalMemory {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_PhysicalMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	private static boolean tagCounter = false;
	private static boolean bankCounter = false;
	
	public static List<String> getTagOrBank() throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		List<String> memoryTag = new ArrayList<>();
		List<String> memoryBank = new ArrayList<>();
		
		String[] tagCommand = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PhysicalMemory | Select-Object Tag | Format-List"};
		String[] bankCommand = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PhysicalMemory | Select-Object BankLabel | Format-List"};
		
		Process tagProcess = Runtime.getRuntime().exec(tagCommand);
		Process bankProcess = Runtime.getRuntime().exec(bankCommand);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(tagProcess.getInputStream()));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(bankProcess.getInputStream()));
		String currentLine;
		
		while((currentLine=br.readLine())!=null) {
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				memoryTag.add(currentLine);
		}
		
		while((currentLine=br2.readLine())!=null) {
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				memoryBank.add(currentLine);
		}
		
		//getting error stream
		if(memoryTag.isEmpty()) {
			BufferedReader error = new BufferedReader(new InputStreamReader(tagProcess.getErrorStream()));
			String errorLine;
			List<String> errorList = new ArrayList<>();
			
			while((errorLine=error.readLine())!=null)
				if(!errorLine.isBlank() || !errorLine.isEmpty())
					errorList.add(errorLine);
			
			error.close();
			ErrorLog errorLog = new ErrorLog();
			
			errorLog.log("\n"+classname+"-"+methodName+"\n"+errorList.toString()+"\n\n");
		}
		
		//getting error stream
		if(memoryBank.isEmpty()) {
			BufferedReader error = new BufferedReader(new InputStreamReader(bankProcess.getErrorStream()));
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
		for(int i=0 ; i<memoryTag.size(); i++) {
			memoryTag.set(i, memoryTag.get(i).substring(memoryTag.get(i).indexOf(":")+1).strip());
		}
		
		for(int i=0 ; i<memoryBank.size(); i++) {
			memoryBank.set(i, memoryBank.get(i).substring(memoryBank.get(i).indexOf(":")+1).strip());
		}
		br.close();
		br2.close();
		
		if(!memoryTag.contains("") || !memoryTag.isEmpty()) {
			tagCounter = true;
			return memoryTag;
		}	
		else {
			bankCounter = true;
			return memoryBank;
		}
	}
	
	public static Map<String, String> getMemory(String memoryID) throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Map<String, String> memory = new HashMap<>();
		String property = "";
		if(tagCounter)
			property = "Tag";
		else if(bankCounter)
			property = "BankLabel";
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PhysicalMemory | Where-Object {$_."+property+" -eq '"+memoryID+"'} | Select-Object Name, Manufacturer, Model, OtherIdentifyingInfo, PartNumber, Tag, FormFactor, BankLabel, Capacity, DataWidth, Speed, ConfiguredClockSpeed, DeviceLocator, SerialNumber | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				memory.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
		br.close();
		
		//getting error stream
		if(memory.isEmpty()) {
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
		return memory;
	}
	
}
