package com.egg.wmic.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CPU{
	private static Process cpu;
	
	protected static String getValues(String WMIC_Attribute) throws IOException {
		String[] command = {"cmd", "/c", "wmic cpu get "+WMIC_Attribute+" /format:list"};
		cpu = Runtime.getRuntime().exec(command);
		BufferedReader br = new BufferedReader(new InputStreamReader(cpu.getInputStream()));
		
		String currentLine;
		String actualName = null;
		
		while((currentLine=br.readLine())!=null)
			if(!currentLine.isBlank() || !currentLine.isEmpty())
				actualName = currentLine;
		
		br.close();
		return actualName.substring(actualName.indexOf("=")+1).strip();
	}
}