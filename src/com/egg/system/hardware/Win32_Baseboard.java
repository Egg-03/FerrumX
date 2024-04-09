package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.formatter.cim.CIM;
import com.egg.system.logger.ErrorLog;

public class Win32_Baseboard {
	private static String classname = "Win32_Baseboard";
	private Win32_Baseboard() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String,String> getMotherboard() throws IOException {
		String methodName = "getMotherboard()";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_Baseboard | Select-Object Manufacturer, Model, Product, SerialNumber, Version | Format-List"};
		
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
		Map<String, String> mbProperty = new LinkedHashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					mbProperty.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					mbProperty.replace(key, value.concat(currentLine.strip()));
			}
				
		br.close();
		
		//get motherbard's plug-n-play deviceID from another class 
		mbProperty.put("PNPDeviceID", CIM.getValues("Win32_MotherBoardDevice", "PNPDeviceID"));
		
		return mbProperty;
	}
}
