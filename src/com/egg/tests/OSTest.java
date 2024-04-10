package com.egg.tests;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.egg.system.operating_system.Win32_OperatingSystem;

public class OSTest {

	public static void main(String[] args) {
		List<String> oslist = null;
		Map<String, String> osinfo = null;
		
		try {
		oslist = Win32_OperatingSystem.getOSList();	
		for(String currentOS : oslist) {
			osinfo = Win32_OperatingSystem.getOSInfo(currentOS);
			
			for(Map.Entry<String, String> entry: osinfo.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
		}
	  }catch (IOException | StringIndexOutOfBoundsException | NullPointerException | ArrayIndexOutOfBoundsException e) {
			System.err.println(e);
		  	osinfo = Collections.emptyMap();
			oslist = Collections.emptyList();
	  }
	}
}
