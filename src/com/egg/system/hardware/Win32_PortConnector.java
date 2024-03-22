package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//represents a set of all the ports available in a motherboard
public class Win32_PortConnector {
	private Win32_PortConnector() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getBaseboardPortID() throws IOException{
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PortConnector | Select-Object Tag | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		List<String> portID = new ArrayList<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				portID.add(currentLine.substring(currentLine.indexOf(":")+1).strip());
				
		br.close();
		return portID;
	}
	
	public static Map<String, String> getBaseboardPorts(String portID) throws IOException{
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PortConnector | Where-Object {$_.Tag -eq '"+portID+"'} | Select-Object Tag, ExternalReferenceDesignator, InternalReferenceDesignator | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> ioPorts = new HashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				ioPorts.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
				
		br.close();
		return ioPorts;
	}
}
