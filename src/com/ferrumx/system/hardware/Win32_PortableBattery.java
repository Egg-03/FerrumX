package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class contains methods that query Win32_PortableBattery class of WMI to
 * fetch Battery information. Can be used alongside the
 * {@link com.ferrumx.system.hardware.Win32_Battery } class.
 * <p>
 * Fetches the following properties: Caption, Name, DeviceID, BatteryStatus,
 * BatteryChemistry, DesignCapacity, DesignVoltage details of your Battery
 * <p>
 * Version
 *
 * @author Egg-03
 * @version 1.2.5
 */
public class Win32_PortableBattery {
	private static String classname = "Win32_PortableBattery";
	private static String attributes = "Caption, Name, DeviceID, BatteryStatus, Chemistry, DesignCapacity, DesignVoltage";

	private Win32_PortableBattery() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method calls the
	 * {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)} function and
	 * passes the WMI Classname and the properties whose values we want to fetch, as
	 * parameters
	 *
	 * @return the Caption, Name, DeviceID, BatteryStatus, BatteryChemistry,
	 *         DesignCapacity, DesignVoltage details of your Battery in the form of
	 *         a {@link java.util.Map}
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */

	public static Map<String, String> getPortableBattery() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.get(classname, attributes);
	}
}
