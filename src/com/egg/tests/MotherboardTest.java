package com.egg.tests;

import java.io.IOException;
import java.util.Map;

import com.egg.system.hardware.Win32_Baseboard;

public class MotherboardTest {

	public static void main(String[] args) throws IOException {
		
		Map<String, String> motherboard = Win32_Baseboard.getMotherboard();
		for(Map.Entry<String, String> entry: motherboard.entrySet())
			System.out.println(entry.getKey()+": "+entry.getValue());
	}

}
