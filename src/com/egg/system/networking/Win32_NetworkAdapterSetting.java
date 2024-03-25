package com.egg.system.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Relates a network adapter and its configuration settings.
public class Win32_NetworkAdapterSetting {
	private Win32_NetworkAdapterSetting() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getIndex(String deviceID) throws IOException {
		String index = "";
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_NetworkAdapterSetting |Where-Object {$_.Element.DeviceID -eq '"+deviceID+"'} | Select-Object Setting | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				index = currentLine;
			
		br.close();
		return index.substring(index.indexOf("=")+1, index.indexOf(")")).trim();
	}
}
