package com.egg.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

public class Win32_SystemDriver {
	private static String classname = new Object() {}.getClass().getName();
	private Win32_SystemDriver() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDrivers() throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName Win32_SystemDriver | Select-Object Caption, PathName, State | Format-List"};
		
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
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		List<String> propertyValues = new ArrayList<>();
		
		while((currentLine=br.readLine())!=null)
				propertyValues.add(currentLine);
				
		br.close();
		
		return propertyValues;
	}
}
	
