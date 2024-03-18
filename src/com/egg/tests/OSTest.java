package com.egg.tests;

import java.io.IOException;

import com.egg.system.operating_system.Win32_OperatingSystem;

public class OSTest {

	public static void main(String[] args) {
		//Java System.properties
		System.out.println(Win32_OperatingSystem.getName());
		System.out.println(Win32_OperatingSystem.getVersion());
		System.out.println(Win32_OperatingSystem.getArchitecture());
		
		//WMIC
		try {
			System.out.println(Win32_OperatingSystem.getWMICName());
			System.out.println(Win32_OperatingSystem.getWMICArchitecture());
			System.out.println(Win32_OperatingSystem.getBuildNumber());
			System.out.println(Win32_OperatingSystem.getInstallDate());
			System.out.println(Win32_OperatingSystem.getManufacturer());
			System.out.println(Win32_OperatingSystem.getWMICName());
			System.out.println(Win32_OperatingSystem.getSystemDrive());
			System.out.println(Win32_OperatingSystem.getWindowsDirectory());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
