package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;

/**
 * This class contains methods that query Win32_Baseboard and
 * Win32_MotherboardDevice classes of WMI to fetch Motherboard information.
 * <p>
 * Fetches the following properties: Manufacturer, Model, Product, SerialNumber,
 * Version
 *
 * @author Egg-03
 */
public class Win32_Baseboard {
	private static String classname = "Win32_Baseboard";
	private static String attributes = "Manufacturer, Model, Product, SerialNumber, Version";

	private Win32_Baseboard() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method calls the
	 * {@link com.ferrumx.formatter.cim.CIM_ML#getPropertiesAndTheirValues(String, String)} function and
	 * passes the WMI Classname and the properties whose values we want to fetch, as
	 * parameters
	 *
	 * @return the Manufacturer, Model, Product, SerialNumber, Version details of
	 *         your Motherboard in the form of a {@link java.util.Map}
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertiesAndTheirValues(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getPropertiesAndTheirValues(String, String)}
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
	public static Map<String, String> getMotherboard() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		Map<String, String> mbProperty = CIM_ML.getPropertiesAndTheirValues(classname, attributes);

		// get motherbard's plug-n-play deviceID from another class
		mbProperty.put("PNPDeviceID", CIM_SL.getPropertyValue("Win32_MotherBoardDevice", "PNPDeviceID"));

		return mbProperty;
	}
}
