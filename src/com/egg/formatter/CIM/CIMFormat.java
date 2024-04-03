package com.egg.formatter.CIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.egg.system.logger.ErrorLog;

class CIMFormat {
	private static String classname = new Object() {}.getClass().getName();
	private CIMFormat() {
		throw new IllegalStateException("Utility Class");
	}
	
	private static Process process; //if, after project completion, this variable is not used outside of any local scope, move it to the local scope
		
	private static String runCommand(String WMI_Class, String WMI_Attribute) throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" | Select-Object "+WMI_Attribute+" | Format-List"};
		process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				if(currentLine.contains(" : "))
					actualName = currentLine;
				else
					actualName.concat(currentLine);
			
		br.close();
		//getting error stream
				if(actualName.isEmpty()) {
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
		return actualName.substring(actualName.indexOf(":")+1).strip();
	}
	
	private static String runCommand(String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" Where-Object "+whereCondition+" | Select-Object "+WMI_Attribute+" | Format-List"};
		process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				if(currentLine.contains(" : "))
					actualName = currentLine;
				else
					actualName.concat(currentLine);
			
		br.close();
		
		//getting error stream
				if(actualName.isEmpty()) {
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
		return actualName.substring(actualName.indexOf(":")+1).strip();
		}
		
		
	//access the private method runCommand() from here
	protected static String accessrunCommand (String WMI_Class, String WMI_Attribute) throws IOException{
		return runCommand(WMI_Class, WMI_Attribute);
	}
		
	protected static String accessrunCommand (String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException{
		return runCommand(WMI_Class, whereCondition, WMI_Attribute);
	}
}
