package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class Win32_VideoController {
	private static String classname = "Win32_VideoController";
	private Win32_VideoController() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getGPUID() throws IOException, IndexOutOfBoundsException{
		String methodName = "getGPUID()";
		List<String> gpuID = new ArrayList<>();
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_VideoController | Select-Object DeviceID | Format-List"};
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
				return Collections.emptyList();
			}
		}catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n"+classname+"-"+methodName+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		int lastIndex;
		String value = "";
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					gpuID.add(value =currentLine);
				else {
					lastIndex = gpuID.size()-1;
					gpuID.set(lastIndex, gpuID.get(lastIndex).concat(value));
				}
			}
				
		br.close();
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<gpuID.size(); i++) {
			gpuID.set(i, gpuID.get(i).substring(gpuID.get(i).indexOf(":")+1).strip());
		}
		return gpuID;		
	}
	
	public static Map<String, String> getGPU(String gpuID) throws IOException, IndexOutOfBoundsException{
		String methodName = "getGPU(String gpuID)";
		Map<String, String> gpu = new LinkedHashMap<>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_VideoController | Where-Object {$_.DeviceID -eq '"+gpuID+"'} | Select-Object Name, PNPDeviceID, CurrentBitsPerPixel, CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate, MaxRefreshRate, MinRefreshRate, AdapterDACType, AdapterRAM, DriverDate, DriverVersion, VideoProcessor | Format-List"};
		
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
				return Collections.emptyMap();
			}
		}catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n"+classname+"-"+methodName+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					gpu.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					gpu.replace(key, value.concat(currentLine.strip()));
			}
		br.close();
		
		return gpu;
	}
}