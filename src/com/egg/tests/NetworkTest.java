package com.egg.tests;

import java.util.ArrayList;
import java.util.HashMap;
import com.egg.system.networking.Win32_NetworkAdapter;

public class NetworkTest {
	public static void main(String[] args) throws Exception {
		//NetworkInfo.NetworkAdapters();
		
		ArrayList<String> deviceIDs = Win32_NetworkAdapter.getDeviceIDList();
		HashMap<String, String> networkAdapter, networkAdapterConfiguration;
		
		for(String currentID : deviceIDs) {
			networkAdapter = Win32_NetworkAdapter.getNetworkAdapters(currentID);
			networkAdapterConfiguration = Win32_NetworkAdapter.getAdapterConfiguration(currentID);
			for(String key: networkAdapter.keySet())
				System.out.println(key+": "+networkAdapter.get(key));
			
			for(String key: networkAdapterConfiguration.keySet())
				System.out.println(key+": "+networkAdapterConfiguration.get(key));
		}
			
	}
}
