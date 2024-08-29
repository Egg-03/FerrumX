package com.ferrumx.ui.primary;

import java.io.IOException;
import java.util.Map;

import javax.swing.JTextField;

import com.ferrumx.system.hardware.Win32_Battery;
import com.ferrumx.system.hardware.Win32_PortableBattery;
import com.ferrumx.ui.secondary.ExceptionUI;

final class Battery {
	private Battery() {
		throw new IllegalStateException("Utility Class");
	}
	
	protected static void initializeBattery(JTextField... batteryFields) {
		try {
			Map<String, String> battery = Win32_Battery.getBattery();
			Map<String, String> portableBattery = Win32_PortableBattery.getPortableBattery();
			
			if(!battery.isEmpty()) {
				batteryFields[0].setText(battery.get("Caption"));
				batteryFields[1].setText(battery.get("Status"));
				batteryFields[2].setText(battery.get("BatteryStatus"));
				batteryFields[3].setText(battery.get("Chemistry"));
				batteryFields[4].setText(battery.get("EstimatedChargeRemaining"));
				batteryFields[5].setText(battery.get("EstimatedRunTime"));
			}
			
			if(!portableBattery.isEmpty()) {
				batteryFields[6].setText(portableBattery.get("Name"));
				batteryFields[7].setText(portableBattery.get("DeviceID"));
				batteryFields[8].setText(portableBattery.get("DesignCapacity"));
				batteryFields[9].setText(portableBattery.get("DesignVoltage"));
			}
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Battery Initialization Error", e.getMessage()).setVisible(true);
		}
		
	}
}
