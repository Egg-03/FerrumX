package com.ferrumx.tests;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.system.hardware.Win32_Battery;

public class BatteryTest {

	public static void main(String[] args) throws IndexOutOfBoundsException, IOException {
		Map<String, String> Battery = Win32_Battery.getBattery();
		for(Map.Entry<String, String> batteryProperty: Battery.entrySet()) {
			System.out.println(batteryProperty.getKey()+": "+batteryProperty.getValue());
		}
	}

}
