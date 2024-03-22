package com.egg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.system.hardware.Win32_VideoController;

public class VideoControllerTest {

	public static void main(String[] args) throws IOException {
		List<String> gpuIDs = Win32_VideoController.getGPUID();
		Map<String, String> currentGPU;
		
		System.out.println(gpuIDs);
		
		for(String currentID : gpuIDs) {
			currentGPU = Win32_VideoController.getGPU(currentID);
			for(Map.Entry<String, String> entry: currentGPU.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
		}
	}
}
