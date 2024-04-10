package com.egg.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

class CIMFormat {
	private static String classname = CIMFormat.class.getClass().getName();
	private CIMFormat() {
		throw new IllegalStateException("Utility Class");
	}
	
	private static String runCommand(String WMI_Class, String WMI_Attribute) throws IOException, IndexOutOfBoundsException {
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
	
	private static String runCommand(String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException, IndexOutOfBoundsException {
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
		
		
	//access the private method runCommand() from here
	protected static String accessrunCommand (String WMI_Class, String WMI_Attribute) throws IOException, IndexOutOfBoundsException{
		return runCommand(WMI_Class, WMI_Attribute);
	}
		
	protected static String accessrunCommand (String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException, IndexOutOfBoundsException{
		return runCommand(WMI_Class, whereCondition, WMI_Attribute);
	}
}
