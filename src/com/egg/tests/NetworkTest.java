package com.egg.tests;

import com.egg.system.networking.Win32_NetworkAdapter;

public class NetworkTest {
	public static void main(String[] args) throws Exception {
		//NetworkInfo.NetworkAdapters();
		
		System.out.println(Win32_NetworkAdapter.getDeviceIDList());
	}
}
