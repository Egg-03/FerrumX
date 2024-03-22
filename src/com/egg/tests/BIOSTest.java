package com.egg.tests;

import java.io.IOException;
import java.util.Map;
import com.egg.system.hardware.Win32_BIOS;

public class BIOSTest {
	
	public static void main(String[] args) throws IOException {
		Map<String, String> BIOS = Win32_BIOS.getPrimaryBIOS();
		for(Map.Entry<String, String> entry: BIOS.entrySet())
			System.out.println(entry.getKey()+": "+entry.getValue());
	}

}
