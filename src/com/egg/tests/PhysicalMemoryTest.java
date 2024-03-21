package com.egg.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.egg.system.hardware.Win32_PhysicalMemory;

public class PhysicalMemoryTest {
	public static void main(String[] args) throws IOException {
		ArrayList<String> memoryID = Win32_PhysicalMemory.getTagOrBank();
		HashMap<String, String> memory = null;
		
		System.out.println(memoryID);
		
		for(String id:memoryID) {
			memory = Win32_PhysicalMemory.getMemory(id);
			for(String key:memory.keySet())
				System.out.println(key+": "+memory.get(key));
			System.out.println();
		}
			
		
		
	}
}
