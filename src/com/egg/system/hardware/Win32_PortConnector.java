package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

//represents a set of all the ports available in a motherboard
public class Win32_PortConnector {
	private static String classname = "Win32_PortConnector()";
	private Win32_PortConnector() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getBaseboardPortID() throws IOException, IndexOutOfBoundsException{
		String methodName = "getBaseboardPortID()";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PortConnector | Select-Object Tag | Format-List"};
		
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
		List<String> portID = new ArrayList<>();
		
		String value="";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					portID.add(value=currentLine.substring(currentLine.indexOf(":")+1).strip());
				else {
					int lastIndex = portID.size()-1;
					portID.set(lastIndex, portID.get(lastIndex).concat(value));
				}
			}
				
				
		br.close();
		
		return portID;
	}
	
	public static Map<String, String> getBaseboardPorts(String portID) throws IOException, IndexOutOfBoundsException{
		String methodName = "getBaseboardPorts()";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_PortConnector | Where-Object {$_.Tag -eq '"+portID+"'} | Select-Object Tag, ExternalReferenceDesignator, InternalReferenceDesignator | Format-List"};
		
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
		Map<String, String> ioPorts = new LinkedHashMap<>();
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					ioPorts.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					ioPorts.replace(key, value.concat(currentLine.strip()));
			}
		br.close();
		
		return ioPorts;
	}
}
