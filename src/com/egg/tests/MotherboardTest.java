package com.egg.tests;

import java.io.IOException;
import java.util.HashMap;

import com.egg.system.hardware.Win32_Baseboard;

public class MotherboardTest {

	public static void main(String[] args) throws IOException {
		
		HashMap<String, String> motherboard = Win32_Baseboard.getMotherboard();
		for(String key: motherboard.keySet())
			System.out.println(key+": "+motherboard.get(key));
	}

}
