package com.ferrumx.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.system.logger.ErrorLog;

public class CIM_SL {
	private static String classname = CIM_SL.class.getClass().getName();
	private CIM_SL() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String get(String WMI_Class, String WMI_Attribute) throws IOException, IndexOutOfBoundsException {
		String methodName = "runCommand(String WMI_Class, String WMI_Attribute)";
		
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" | Select-Object "+WMI_Attribute+" | Format-List"};
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
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					actualName = currentLine;
				else
					actualName=actualName.concat(currentLine);
			}
				
			
		br.close();
		
		return actualName.substring(actualName.indexOf(":")+1).strip();
	}
	
	public static String getWhere(String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException, IndexOutOfBoundsException {
		String methodName = "runCommand(String WMI_Class, String whereCondition, String WMI_Attribute)";
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" Where-Object "+whereCondition+" | Select-Object "+WMI_Attribute+" | Format-List"};
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
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					actualName = currentLine;
				else
					actualName=actualName.concat(currentLine);
			}
		br.close();
		return actualName.substring(actualName.indexOf(":")+1).strip();
	}
}
