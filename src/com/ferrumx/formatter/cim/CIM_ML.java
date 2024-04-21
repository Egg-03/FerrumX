package com.ferrumx.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ferrumx.system.logger.ErrorLog;

/**
 * This class queries all the WMI Classes based on the attributes passed to it's one of the four methods called by the methods in other packages.
 * <p>
 * Supports Multi-line parsing of output from the powershell
 * Is recommended for extracting multiple properties at once
 * For single property extraction, see {@link CIM_SL}
 * @author Egg-03
 * @version 1.1.0
 */
public class CIM_ML {
	private CIM_ML() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class} | Select-Object {@literal Key} | Format-List
	 * where the parameters are provided by the calling methods
	 * @param win32Class the classname passed to by the method calling it
	 * @param Key passed to by the method calling it
	 * @return a list of values requested by the method calling it. The values returned are of the property {@literal Key}
	 * @throws IOException in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from powershell
	 */
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
	
	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class} | Where-Object {$_.{@literal determinantProperty} -eq {@literal determinantValue}} | Select-Object {@literal extractProperty} | Format-List
	 * where the parameters are provided by the calling methods
	 * @param win32Class the classname passed to by the calling method
	 * @param determinantProperty a filtering parameter, passed to by the calling method
	 * @param determinantValue the value of the filtering parameter, also passed to by the calling method
	 * @param extractProperty the property to be extracted, provided by the calling method.
	 * @return a list of values requested by the method calling it. The values returned are the values of the property {@literal extractProperty}
	 * @throws IOException in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from powershell
	 */
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
	
	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class} | Select-Object {@literal attributes} | Format-List
	 * where the parameters are provided by the calling methods
	 * @param win32Class the classname passed to by the calling method
	 * @param attributes a list of properties requested for a particular class. The properties requested by the calling methods can be found in their respective class descriptions
	 * @return a {@link java.util.Map} of the attribute values requested by the calling method
	 * @throws IOException in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from powershell
	 */
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
	
	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class} | Where-Object {$_.{@literal determinantProperty} -eq {@literal determinantValue}} | Select-Object {@literal extractProperty} | Format-List
	 * where the parameters are provided by the calling methods
	 * @param win32Class win32Class the classname passed to by the calling method
	 * @param determinantProperty a filtering parameter, passed to by the calling method
	 * @param determinantValue the value of the filtering parameter, also passed to by the calling method
	 * @param attributes a list of properties requested for a particular class. The properties requested by the calling methods can be found in their respective class descriptions
	 * @return a {@link java.util.Map} of the attribute values requested by the calling method
	 * @throws IOException in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from powershell
	 */
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
