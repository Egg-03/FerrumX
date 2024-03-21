package com.egg.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.egg.system.hardware.Win32_VideoController;

public class VideoControllerTest {

	public static void main(String[] args) throws IOException {
		ArrayList<String> gpuIDs = Win32_VideoController.getGPUID();
		HashMap<String, String> currentGPU;
		
		System.out.println(gpuIDs);
		
		for(String currentID : gpuIDs) {
			currentGPU = Win32_VideoController.getGPU(currentID);
			for(String key: currentGPU.keySet())
				System.out.println(key+": "+currentGPU.get(key));
		}
	}
}
