package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.system.hardware.Win32_PhysicalMemory;

public class PhysicalMemoryTest {
	public static void main(String[] args) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		List<String> memoryID = Win32_PhysicalMemory.getTag();
		Map<String, String> memory = null;

		System.out.println(memoryID);

		for (String id : memoryID) {
			memory = Win32_PhysicalMemory.getMemory(id);
			for (Map.Entry<String, String> entry : memory.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}
}
