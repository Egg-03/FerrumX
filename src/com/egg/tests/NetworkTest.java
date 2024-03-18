package com.egg.tests;

import java.util.ArrayList;

import com.egg.system.networking.NetworkInfo;
import com.egg.system.networking.Win32_NetworkAdapter;

public class NetworkTest {
	public static void main(String[] args) throws Exception {
		NetworkInfo.NetworkAdapters();
		
		ArrayList<String> deviceIDs = Win32_NetworkAdapter.getDeviceIDList();
		
		for(String currentID : deviceIDs)
			System.out.println(Win32_NetworkAdapter.getNetworkAdapters(currentID));
		
	}
}
