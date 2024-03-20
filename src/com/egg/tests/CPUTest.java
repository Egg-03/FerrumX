package com.egg.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.egg.system.hardware.Win32_Processor;


public class CPUTest {
	public static void main(String[] args) throws IOException{
		ArrayList<String> deviceIDs = Win32_Processor.getDeviceIDList();
		HashMap<String, String> currentCPU;
		
		//System.out.println(deviceIDs);
		
		for(String currentID : deviceIDs) {
			currentCPU = Win32_Processor.getCurrentProcessor(currentID);
			for(String key: currentCPU.keySet())
				System.out.println(key+": "+currentCPU.get(key));
	    }
     }
}
	