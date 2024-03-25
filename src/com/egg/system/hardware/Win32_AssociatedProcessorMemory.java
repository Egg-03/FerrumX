package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//this class servers as a relationship between Win32_Processor and Win32_CacheMemory
//this class will server the CacheMemory IDs (L1, L2 and L3 ID) of a CPU based on the CPU ID given by Win32_Processor
//The ID's gained from this class will then be used in WIn32_CacheMemory to retrieve the related information
public class Win32_AssociatedProcessorMemory {
	
	private Win32_AssociatedProcessorMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getCacheID(String cpuID) throws IOException{
		String command[] = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_AssociatedProcessorMemory | Where-Object {$_.Dependent.DeviceID -eq '"+cpuID+"'} | Select-Object Antecedent | Format-List"};
		
		List<String> cacheIDList = new ArrayList<>();
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				cacheIDList.add(currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\"")));
		br.close();
		return cacheIDList;
	}
}
