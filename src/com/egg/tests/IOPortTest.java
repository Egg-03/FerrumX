package com.egg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.system.hardware.Win32_PortConnector;

public class IOPortTest {

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		List<String> portID = Win32_PortConnector.getBaseboardPortID();
		Map<String, String> ports = null;
		
		for(String id:portID) {
			ports = Win32_PortConnector.getBaseboardPorts(id);
			for(Map.Entry<String, String> port: ports.entrySet())
				System.out.println(port.getKey()+": "+port.getValue());
			System.out.println();
		}	
	}

}
