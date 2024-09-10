package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_Printer class of WMI and represents the
 * installed printers in your device.
 * <p>
 * Fetches the following properties: Name, HorizontalResolution,
 * VerticalResolution, Capabilities, CapabilityDescriptions, Default,
 * DriverName, Hidden, Local, Network, PortName, PrintProcessor, Shared,
 * ShareName, SpoolEnabled, WorkOffline
 *
 * @author Egg-03
 * @version 1.3.0
 */
public class Win32_Printer {
	private static String classname = "Win32_Printer";
	private static String attributes = "Name, HorizontalResolution, VerticalResolution, Capabilities, CapabilityDescriptions, Default, DriverName, Hidden, Local, Network, PortName, PrintProcessor, Shared, ShareName, SpoolEnabled, WorkOffline";

	private Win32_Printer() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method fetches a list of printer deviceIDs
	 *
	 * @return a {@link java.util.List} of printer deviceIDs
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
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
	public static List<String> getDeviceIDList() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getID(classname, "DeviceID");
	}

	/**
	 * This method returns Printer properties of the deviceID fetched to it
	 *
	 * @param deviceID fetched from {@link Win32_Printer#getDeviceIDList()}
	 * @return a {@link java.util.Map} of the printer properties mentioned in the
	 *         class description
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getWhere(String, String, String, String)}
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
	public static Map<String, String> getCurrentPrinter(String deviceID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getWhere(classname, "DeviceID", deviceID, attributes);
	}
}
