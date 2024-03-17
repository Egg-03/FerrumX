package com.egg.tests;

import java.io.IOException;

import com.egg.system.os.OS;

public class OSTest {

	public static void main(String[] args) {
		//Java System.properties
		System.out.println(OS.getName());
		System.out.println(OS.getVersion());
		System.out.println(OS.getArchitecture());
		
		//WMIC
		try {
			System.out.println(OS.getWMICName());
			System.out.println(OS.getWMICArchitecture());
			System.out.println(OS.getBuildNumber());
			System.out.println(OS.getInstallDate());
			System.out.println(OS.getManufacturer());
			System.out.println(OS.getWMICName());
			System.out.println(OS.getSystemDrive());
			System.out.println(OS.getWindowsDirectory());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
