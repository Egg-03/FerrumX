package com.ferrumx.system.networking;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_NetworkAdapterConfiguration class of WMI and fetches the following properties associated with a Network Adapter
 * that the {@link com.ferrumx.system.hardware.Win32_NetworkAdapter} class cannot provide:
 * IPEnabled, IPAddress, IPSubnet, DefaultIPGateway, DHCPEnabled, DHCPServer, DHCPLeaseObtained, DHCPLeaseExpires, DNSHostName, DNSServerSearchOrder
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_NetworkAdapterConfiguration {
	private static String classname = "Win32_NetworkAdapterConfiguration";
	private static String attributes = "IPEnabled, IPAddress, IPSubnet, DefaultIPGateway, DHCPEnabled, DHCPServer, DHCPLeaseObtained, DHCPLeaseExpires, DNSHostName, DNSServerSearchOrder";
	private Win32_NetworkAdapterConfiguration() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * Fetches the properties described in the class description for a particular adapter index
	 * @param adapterIndex fetched from {@link Win32_NetworkAdapterSetting#getIndex(String)}
	 * @return a {@link java.util.Map} of all the properties mentioned in the class description for a particular network adapter
	 * @throws IOException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there are I/O Errors during streaming of data from and to Powershell and other generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)} when there is a parsing error of data fetched from Windows Powershell
	 */
	public static Map<String,String> getAdapterConfiguration(String adapterIndex) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "Index", adapterIndex, attributes);	}	
}
