package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

//this class serves as a relationship between Win32_Processor and Win32_CacheMemory
//this class will server the CacheMemory IDs (L1, L2 and L3 ID) of a CPU based on the CPU ID given by Win32_Processor
//The ID's gained from this class will then be used in WIn32_CacheMemory to retrieve the related information
public class Win32_AssociatedProcessorMemory {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_AssociatedProcessorMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getCacheID(String cpuID) throws IOException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String command[] = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_AssociatedProcessorMemory | Where-Object {$_.Dependent.DeviceID -eq '"+cpuID+"'} | Select-Object Antecedent | Format-List"};
		
		List<String> cacheIDList = new ArrayList<>();
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		String value = "";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				if(currentLine.contains(" : "))
					cacheIDList.add(value=currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\"")));
				else {
					int lastIndex = cacheIDList.size()-1;
					cacheIDList.set(lastIndex, cacheIDList.get(lastIndex).concat(value));
				}
		br.close();
		
		//getting error stream
				if(cacheIDList.isEmpty()) {
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
		return cacheIDList;
	}
}
