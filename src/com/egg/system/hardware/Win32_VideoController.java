package com.egg.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Win32_VideoController {
	private Win32_VideoController() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getGPUID() throws IOException{
		List<String> gpuID = new ArrayList<>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_VideoController | Select-Object DeviceID | Format-List"};
		Process process = Runtime.getRuntime().exec(command);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		
		while((currentLine=br.readLine())!=null) {
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				gpuID.add(currentLine);
		}
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<gpuID.size(); i++) {
			gpuID.set(i, gpuID.get(i).substring(gpuID.get(i).indexOf(":")+1).strip());
		}
		return gpuID;		
	}
	
	public static Map<String, String> getGPU(String gpuID) throws IOException{
		Map<String, String> gpu = new HashMap<>();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_VideoController | Where-Object {$_.DeviceID -eq '"+gpuID+"'} | Select-Object Name, PNPDeviceID, CurrentBitsPerPixel, CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate, MaxRefreshRate, MinRefreshRate, AdapterDACType, AdapterRAM, DriverDate, DriverVersion, VideoProcessor | Format-List"};
		
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				gpu.put(currentLine.substring(0, currentLine.indexOf(":")).strip(), currentLine.substring(currentLine.indexOf(":")+1).strip());
			}
		
		return gpu;
	}
}