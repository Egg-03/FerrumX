package com.egg.tests;

import java.io.IOException;

import com.egg.system.hardware.Win32_Processor;

public class CPUTest {
	public static void main(String[] args){
		try {
			System.out.println("CPU Name: "+Win32_Processor.getName());
			System.out.println("CPU Manufacturer: "+Win32_Processor.getManufacturer());
			System.out.println("CPU Core Count: "+Win32_Processor.getCores());
			System.out.println("CPU Thread Count: "+Win32_Processor.getThreads());
			System.out.println("CPU Socket: "+Win32_Processor.getSocket());
			System.out.println("CPU Address Width: "+Win32_Processor.getAddressWidth()+" bits");
			System.out.println("CPU Rated FSB: "+Win32_Processor.getBusSpeed()+" MHz");
			System.out.println("CPU Max Clock Speed (Base): "+Win32_Processor.getMaxClockSpeed()+" MHz");
			System.out.println("L2 Cache Size: "+Win32_Processor.getL2CacheSize()+" KB");
			System.out.println("L3 Cache Size: "+Win32_Processor.getL3CacheSize()+" KB");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
