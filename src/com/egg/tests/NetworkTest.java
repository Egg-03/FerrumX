package com.egg.tests;

import java.util.List;
import java.util.Map;

import com.egg.system.hardware.Win32_NetworkAdapter;
import com.egg.system.networking.Win32_NetworkAdapterConfiguration;

public class NetworkTest {
	public static void main(String[] args) throws Exception {
		List<String> deviceIDs = Win32_NetworkAdapter.getDeviceIDList();
		Map<String, String> networkAdapter;
		Map<String, String> networkAdapterConfiguration;
		
		System.out.println(deviceIDs);
		
		for(String currentID : deviceIDs) {
			networkAdapter = Win32_NetworkAdapter.getNetworkAdapters(currentID);
			networkAdapterConfiguration = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(currentID);
			for(Map.Entry<String, String> entry: networkAdapter.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println();
			for(Map.Entry<String, String> entry: networkAdapterConfiguration.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println();
		}
			
	}
}
