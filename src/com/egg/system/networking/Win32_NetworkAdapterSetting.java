package com.egg.system.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

//Relates a network adapter and its configuration settings.
public class Win32_NetworkAdapterSetting {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_NetworkAdapterSetting() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getIndex(String deviceID) throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String index = "";
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_NetworkAdapterSetting |Where-Object {$_.Element.DeviceID -eq '"+deviceID+"'} | Select-Object Setting | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				index = currentLine;
			
		br.close();
		//getting error stream
		if(index.isEmpty()) {
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
		return index.substring(index.indexOf("=")+1, index.indexOf(")")).trim();
	}
}
