package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_Desktop class of WMI and represents the Monitor
 * details of your system.
 * <p>
 * Fetches the following properties: Name, PNPDeviceID, Status,
 * MonitorManufacturer, MonitorType, PixelsPerXLogicalInch,
 * PixelsPerYLogicalInch
 *
 * @author Egg-03
 */
public class Win32_DesktopMonitor {
	private static String classname = "Win32_DesktopMonitor";
	private static String attributes = "Name, PNPDeviceID, Status, MonitorManufacturer, MonitorType, PixelsPerXLogicalInch, PixelsPerYLogicalInch";

	private Win32_DesktopMonitor() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method fetches a list of Monitors present in the system based on their
	 * IDs
	 *
	 * @return a {@link java.util.List} of Monitor Device IDs
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertyValue(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertyValue(String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 * @throws ShellException            if any internal command used in the
	 *                                   powershell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	public static List<String> getMonitorID()
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertyValue(classname, "DeviceID");
	}

	/**
	 * This method fetches the Monitor properties based on the given DeviceID
	 *
	 * @param deviceID fetched from {@link Win32_DesktopMonitor#getMonitorID()}
	 * @return a {@link java.util.Map} of Monitor properties and their values
	 *         mentioned in the class description
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertiesAndTheirValuesWhere(String, String, String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertiesAndTheirValuesWhere(String, String, String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 * @throws ShellException            if any internal command used in the
	 *                                   powershell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	public static Map<String, String> getMonitorProperties(String deviceID)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertiesAndTheirValuesWhere(classname, "DeviceID", deviceID, attributes);
	}
}
