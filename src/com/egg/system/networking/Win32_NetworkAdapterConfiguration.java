package com.egg.system.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Win32_NetworkAdapterConfiguration {
	private Win32_NetworkAdapterConfiguration() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String,String> getAdapterConfiguration(String adapterIndex) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_NetworkAdapterConfiguration | Where-Object {$_.Index -eq '"+adapterIndex+"'} | Select-Object DHCPLeaseObtained, DHCPLeaseExpires, DHCPEnabled, DHCPServer, DNSHostName, DNSServerSearchOrder, IPAddress, IPEnabled, DefaultIPGateway, IPSubnet | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				propertyValues.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
				
			
		br.close();
		return propertyValues;
	}
	
}
