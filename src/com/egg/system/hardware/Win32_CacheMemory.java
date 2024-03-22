package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Win32_CacheMemory {
	private Win32_CacheMemory() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getCPUCache(String cacheID) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_CacheMemory | Where-Object {$_.DeviceID -eq '"+cacheID+"'} | Select-Object DeviceID, InstalledSize, Purpose, Associativity | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> cache = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				cache.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
				
			
		br.close();
		return cache;
	}
}
