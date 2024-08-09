package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class contains methods that query the Win32_NetworkAdapter class of WMI
 * to fetch the Network Adapters that a installed and active.
 * <p>
 * The following properties are fetched: Name, Description, PNPDeviceID,
 * MACAddress, Installed, NetEnabled, NetConnectionID, PhysicalAdapter,
 * TimeOfLastReset
 *
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_NetworkAdapter {
	private static String classname = "Win32_NetworkAdapter";
	private static String attributes = "Name, Description, PNPDeviceID, MACAddress, Installed, NetEnabled, NetConnectionID, PhysicalAdapter, TimeOfLastReset";

	private Win32_NetworkAdapter() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches a list of DeviceIDs of the Network Adapters that are active
	 *
	 * @return a {@link java.util.List} of all the active Adapter DeviceIDs
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getIDWhere(String, String, String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getIDWhere(String, String, String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */
	public static List<String> getDeviceIDList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getIDWhere(classname, "NetEnabled", "True", "DeviceID");
	}

	/**
	 * Fetches the properties mentioned in the class description based on the
	 * DeviceID of the adapter passed onto it
	 *
	 * @param deviceID the network adapter ID fetched from
	 *                 {@link Win32_NetworkAdapter#getDeviceIDList()}
	 * @return a {@link java.util.Map} of the properties and their values mentioned
	 *         in the class description
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */
	public static Map<String, String> getNetworkAdapters(String deviceID)
			throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}

}
