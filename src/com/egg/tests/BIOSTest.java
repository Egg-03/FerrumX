package com.egg.tests;

import java.io.IOException;
import java.util.HashMap;

import com.egg.system.hardware.Win32_BIOS;

public class BIOSTest {

	public static void main(String[] args) throws IOException {
		HashMap<String, String> BIOS = Win32_BIOS.getPrimaryBIOS();
		for(String key: BIOS.keySet())
			System.out.println(key+": "+BIOS.get(key));
	}

}
