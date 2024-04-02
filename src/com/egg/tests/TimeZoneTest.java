package com.egg.tests;

import java.io.IOException;
import java.util.Map;

import com.egg.system.operating_system.Win32_TimeZone;


public class TimeZoneTest {

	public static void main(String[] args) throws IOException {
		Map<String, String> currentTimeZone = Win32_TimeZone.getOSTimeZone();
		
			for(Map.Entry<String, String> entry: currentTimeZone.entrySet())
				System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println();
	    }
	}
