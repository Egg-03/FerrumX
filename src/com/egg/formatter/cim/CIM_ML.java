package com.egg.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.egg.system.logger.ErrorLog;

public class CIM_ML {
	private CIM_ML() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getID(String win32Class, String Key) throws IOException, IndexOutOfBoundsException{
		String[] command = {"powershell.exe","/c", "Get-CimInstance -ClassName "+win32Class+" | Select-Object "+Key+" | Format-List"};
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
				
				ErrorLog cmdError = new ErrorLog();
				cmdError.log("\n"+win32Class+"-"+Key+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n\n");
				return Collections.emptyList();
			}
			
		}catch (InterruptedException e) {
			System.err.println("\n"+win32Class+"-"+Key+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
			return Collections.emptyList();
		}
		
		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		List<String> id = new ArrayList<>();
		String currentLine;
		String value = "";
		while((currentLine=stream.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					id.add(value =currentLine);
				else {
					int lastIndex = id.size()-1;
					id.set(lastIndex, id.get(lastIndex).concat(value));
				}
			}
				
			
		stream.close();
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<id.size(); i++) {
			id.set(i, id.get(i).substring(id.get(i).indexOf(":")+1).strip());
		}
		stream.close();
		return id;
	}
	
	public static List<String> getIDWhere(String win32Class, String determinantProperty, String determinantValue,  String extractProperty) throws IOException, IndexOutOfBoundsException{
		String[] command = {"powershell.exe","/c", "Get-CimInstance -ClassName "+win32Class+" | Where-Object {$_."+determinantProperty+" -eq "+"'"+determinantValue+"'}"+" | Select-Object "+extractProperty+" | Format-List"};
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
				
				ErrorLog cmdError = new ErrorLog();
				cmdError.log("\n"+win32Class+"-"+extractProperty+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n\n");
			}
			
		}catch (InterruptedException e) {
			System.err.println("\n"+win32Class+"-"+extractProperty+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
			return Collections.emptyList();
		}
		
		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		List<String> id = new ArrayList<>();
		String currentLine;
		String value = "";
		while((currentLine=stream.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				if(currentLine.contains(" : "))
					id.add(value =currentLine);
				else {
					int lastIndex = id.size()-1;
					id.set(lastIndex, id.get(lastIndex).concat(value));
				}
			}
				
		stream.close();
		
		//strip the property_name and keep only the property value
		for(int i=0 ; i<id.size(); i++) {
			id.set(i, id.get(i).substring(id.get(i).indexOf(":")+1).strip());
		}
		stream.close();
		return id;
	}
	
	public static Map<String, String> get(String win32Class, String attributes) throws IOException, IndexOutOfBoundsException {
		
		String[] command = {"powershell.exe","/c", "Get-CimInstance -ClassName "+win32Class+" | Select-Object "+attributes+" | Format-List"};
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
				
				ErrorLog cmdError = new ErrorLog();
				cmdError.log("\n"+win32Class+"-"+attributes+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n\n");
				return Collections.emptyMap();
			}
			
		}catch (InterruptedException e) {
			System.err.println("\n"+win32Class+"-"+attributes+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
			return Collections.emptyMap();
		}
		
		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();
		
		while((currentLine=stream.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					propertyValues.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					propertyValues.replace(key, value.concat(currentLine.strip()));
			}
		stream.close();
		return propertyValues;
	}
	
	
	public static Map<String, String> getWhere(String win32Class, String determinantProperty, String determinantValue, String attributes) throws IOException, IndexOutOfBoundsException {
		
		String[] command = {"powershell.exe","/c", "Get-CimInstance -ClassName "+win32Class+" | Where-Object {$_."+determinantProperty+" -eq "+"'"+determinantValue+"'}"+" | Select-Object "+attributes+" | Format-List"};
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
				
				ErrorLog cmdError = new ErrorLog();
				cmdError.log("\n"+win32Class+"-"+determinantProperty+"-"+determinantValue+"-"+attributes+"\n"+errorList.toString()+"\nProcess Exited with code:"+exitCode+"\n\n");
			}
			
		}catch (InterruptedException e) {
			System.err.println("\n"+win32Class+"-"+attributes+"\n"+e.getMessage()+"\n\n");
			Thread.currentThread().interrupt();
			return Collections.emptyMap();
		}
		
		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();
		
		while((currentLine=stream.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if(currentLine.contains(" : "))
					propertyValues.put(key=currentLine.substring(0, currentLine.indexOf(":")).strip(), value =currentLine.substring(currentLine.indexOf(":")+1).strip());
				else
					propertyValues.replace(key, value.concat(currentLine.strip()));
			}
		stream.close();
		return propertyValues;
	}
}
