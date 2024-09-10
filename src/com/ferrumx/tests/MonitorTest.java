package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.system.hardware.Win32_DesktopMonitor;

public class MonitorTest {
	public static void main(String[] args)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		List<String> deviceIDs = Win32_DesktopMonitor.getMonitorID();
		Map<String, String> currentMonitor;

		System.out.println(deviceIDs);

		for (String currentID : deviceIDs) {
			currentMonitor = Win32_DesktopMonitor.getMonitorProperties(currentID);
			for (Map.Entry<String, String> entry : currentMonitor.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}
}
