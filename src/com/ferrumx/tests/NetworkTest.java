package com.ferrumx.tests;

import java.util.List;
import java.util.Map;

import com.ferrumx.system.hardware.Win32_NetworkAdapter;
import com.ferrumx.system.networking.Win32_NetworkAdapterConfiguration;
import com.ferrumx.system.networking.Win32_NetworkAdapterSetting;

public class NetworkTest {
	public static void main(String[] args) throws Exception, IndexOutOfBoundsException {
		List<String> deviceIDs = Win32_NetworkAdapter.getDeviceIDList();
		Map<String, String> networkAdapter;
		Map<String, String> networkAdapterConfiguration;
		String index = "";

		System.out.println(deviceIDs);

		for (String currentID : deviceIDs) {
			networkAdapter = Win32_NetworkAdapter.getNetworkAdapters(currentID);
			index = Win32_NetworkAdapterSetting.getIndex(currentID);
			networkAdapterConfiguration = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(index);
			for (Map.Entry<String, String> entry : networkAdapter.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
			for (Map.Entry<String, String> entry : networkAdapterConfiguration.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}
}
