package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_VideoController class of WMI and represents the
 * basic GPU details of your system.
 * <p>
 * Fetches the following properties: Name, PNPDeviceID, CurrentBitsPerPixel,
 * CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate,
 * MaxRefreshRate, MinRefreshRate, AdapterDACType, AdapterRAM, DriverDate,
 * DriverVersion, VideoProcessor
 *
 * @author Egg-03
 */
public class Win32_VideoController {
	private static String classname = "Win32_VideoController";
	private static String attributes = "Name, PNPDeviceID, CurrentBitsPerPixel, CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate, MaxRefreshRate, MinRefreshRate, AdapterDACType, AdapterRAM, DriverDate, DriverVersion, VideoProcessor";

	private Win32_VideoController() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method fetches a list of GPU IDs found in the system
	 *
	 * @return a {@link java.util.List} of GPU IDs
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
	public static List<String> getGPUID() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertyValue(classname, "DeviceID");
	}

	/**
	 * This method fetches a list of GPUs found in the system, based on their IDs
	 *
	 * @param gpuID fetched from {@link Win32_VideoController#getGPUID()}
	 * @return a {@link java.util.Map} of GPU properties mentioned in the class
	 *         description
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
	public static Map<String, String> getGPU(String gpuID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertiesAndTheirValuesWhere(classname, "DeviceID", gpuID, attributes);
	}
}