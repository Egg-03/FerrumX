package com.egg.formatter.CIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CIMFormat {
	private static Process process; //if, after project completion, this variable is not used outside of any local scope, move it to the local scope
		
	private static String runCommand(String WMI_Class, String WMI_Attribute) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" | Select-Object "+WMI_Attribute+" | Format-List"};
		process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				actualName = currentLine;
			
		br.close();
		return actualName.substring(actualName.indexOf(":")+1).strip();
	}
	
	private static String runCommand(String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException {
		String[] command = {"powershell.exe", "/c", "Get-CimInstance -ClassName "+WMI_Class+" Where-Object "+whereCondition+" | Select-Object "+WMI_Attribute+" | Format-List"};
		process = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String currentLine;
		String actualName = "";
			
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				actualName = currentLine;
			
		br.close();
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
