package com.ferrumx.system.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.system.logger.ErrorLog;

/**
 * This class relates {@link com.ferrumx.system.hardware.Win32_NetworkAdapter} with {@link Win32_NetworkAdapterConfiguration}.
 * <p>
 * Queries the Win32_NetworkAdapterSetting of WMI to fetch the "Setting" property which is used in Win32_NetworkAdapterConfiguration.
 * <p>
 * The linking happens as follows:
 * <p>
 * The NetworkAdapter ID fetched from {@link com.ferrumx.system.hardware.Win32_NetworkAdapter#getDeviceIDList()} gets passed onto {@link Win32_NetworkAdapterSetting#getIndex(String)} which returns the "Setting" property, which is a parameter for {@link Win32_NetworkAdapterConfiguration#getAdapterConfiguration(String)}
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_NetworkAdapterSetting {
	private static String classname = "Win32_NetworkAdapterSetting";
	private Win32_NetworkAdapterSetting() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * Fetches the "Settings" property for a given Network Adapter ID
	 * @param deviceID the adapter ID received from {@link com.ferrumx.system.hardware.Win32_NetworkAdapter#getDeviceIDList()}
	 * @return the "Settings" property for a particular adapter ID
	 * @throws IOException in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from powershell
	 */
	public static String getIndex(String deviceID) throws IOException, IndexOutOfBoundsException {
		String methodName = "getIndex(String deviceID)";
		String setting = "";
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_NetworkAdapterSetting | Where-Object {$_.Element.DeviceID -eq '"+deviceID+"'} | Select-Object Setting | Format-List"};
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
				return "";
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
				if(currentLine.contains(" : "))
					setting = currentLine;
				else
					setting=setting.concat(currentLine);
			}
				
			
		br.close();
		
		return setting.substring(setting.indexOf("=")+1, setting.indexOf(")")).trim();
	}
}
