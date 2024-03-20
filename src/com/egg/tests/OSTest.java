package com.egg.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.egg.system.operating_system.Win32_OperatingSystem;

public class OSTest {

	public static void main(String[] args) throws IOException {
		ArrayList<String> oslist = Win32_OperatingSystem.getOSList();
		HashMap<String, String> osinfo;
		
		for(String currentOS : oslist) {
			osinfo = Win32_OperatingSystem.getOSInfo(currentOS);
			
			for(String key: osinfo.keySet())
				System.out.println(key+": "+osinfo.get(key));
		}
	}
}
