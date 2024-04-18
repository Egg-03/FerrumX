package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.system.hardware.Win32_Processor;


public class CPUTest {
	public static void main(String[] args) throws IOException, IndexOutOfBoundsException{
		List<String> deviceIDs = Win32_Processor.getProcessorList();
		Map<String, String> currentCPU;
		
		System.out.println(deviceIDs);
		
		for(String currentID : deviceIDs) {
			currentCPU = Win32_Processor.getCurrentProcessor(currentID);
			for(Map.Entry<String, String> entry: currentCPU.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println();
	    }
     }
}
	