package com.ferrumx.tests;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.system.hardware.Win32_Battery;
import com.ferrumx.system.hardware.Win32_PortableBattery;

public class BatteryTest {

	public static void main(String[] args) throws IndexOutOfBoundsException, IOException {
		Map<String, String> Battery = Win32_Battery.getBattery();
		for(Map.Entry<String, String> batteryProperty: Battery.entrySet()) {
			System.out.println(batteryProperty.getKey()+": "+batteryProperty.getValue());
		}
		
		Map<String, String> portableBattery = Win32_PortableBattery.getPortableBattery();
		for(Map.Entry<String, String> portableBatteryProperty: portableBattery.entrySet()) {
			System.out.println(portableBatteryProperty.getKey()+": "+portableBatteryProperty.getValue());
		}
	}

}
