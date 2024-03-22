package com.egg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.system.operating_system.Win32_OperatingSystem;

public class OSTest {

	public static void main(String[] args) throws IOException {
		List<String> oslist = Win32_OperatingSystem.getOSList();
		Map<String, String> osinfo;
		
		for(String currentOS : oslist) {
			osinfo = Win32_OperatingSystem.getOSInfo(currentOS);
			
			for(Map.Entry<String, String> entry: osinfo.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
		}
	}
}
