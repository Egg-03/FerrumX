package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.system.operating_system.Win32_OperatingSystem;

public class OSTest {

	public static void main(String[] args) throws IndexOutOfBoundsException, IOException, ShellException, InterruptedException {
		List<String> oslist;
		Map<String, String> osinfo;

		oslist = Win32_OperatingSystem.getOSList();
		for (String currentOS : oslist) {
			osinfo = Win32_OperatingSystem.getOSInfo(currentOS);

			for (Map.Entry<String, String> entry : osinfo.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
