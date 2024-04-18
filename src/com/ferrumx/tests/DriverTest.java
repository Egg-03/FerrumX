package com.ferrumx.tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.ferrumx.system.operating_system.Win32_SystemDriver;

public class DriverTest {
	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		List<String> drivers = Win32_SystemDriver.getDrivers();
		Iterator<String> itr = drivers.iterator();
		
		while(itr.hasNext())
			System.out.println(itr.next());		
	}
}
