package com.egg.system.networking;

import java.io.IOException;
import java.util.Map;

import com.egg.formatter.cim.CIM_ML;

public class Win32_NetworkAdapterConfiguration {
	private static String classname = "Win32_NetworkAdapterConfiguration";
	private static String attributes = "IPEnabled, IPAddress, IPSubnet, DefaultIPGateway, DHCPEnabled, DHCPServer, DHCPLeaseObtained, DHCPLeaseExpires, DNSHostName, DNSServerSearchOrder";
	private Win32_NetworkAdapterConfiguration() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String,String> getAdapterConfiguration(String adapterIndex) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "Index", adapterIndex, attributes);	}	
}
