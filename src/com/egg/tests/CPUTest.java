package com.egg.tests;

import java.io.IOException;

import com.egg.system.cpu.CPU;

public class CPUTest {
	public static void main(String[] args){
		try {
			System.out.println("CPU Name: "+CPU.getName());
			System.out.println("CPU Manufacturer: "+CPU.getManufacturer());
			System.out.println("CPU Core Count: "+CPU.getCores());
			System.out.println("CPU Thread Count: "+CPU.getThreads());
			System.out.println("CPU Socket: "+CPU.getSocket());
			System.out.println("CPU Address Width: "+CPU.getAddressWidth()+" bits");
			System.out.println("CPU Rated FSB: "+CPU.getBusSpeed()+" MHz");
			System.out.println("CPU Max Clock Speed (Base): "+CPU.getMaxClockSpeed()+" MHz");
			System.out.println("L2 Cache Size: "+CPU.getL2CacheSize()+" KB");
			System.out.println("L3 Cache Size: "+CPU.getL3CacheSize()+" KB");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
