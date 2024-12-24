package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class contains methods that query Win32_DiskDrive class of WMI to fetch
 * Disk Information.
 * <p>
 * The class fetches the values of the following properties: Caption, Model,
 * Size, FirmwareRevision, SerialNumber, Partitions, Status, InterfaceType,
 * PNPDeviceID
 *
 * @author Egg-03
 */
public class Win32_DiskDrive {
	private static String classname = "Win32_DiskDrive";
	private static String attributes = "Caption, Model, Size, FirmwareRevision, SerialNumber, Partitions, Status, InterfaceType, PNPDeviceID";

	private Win32_DiskDrive() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches a list of Disk Drives based on their DeviceID property This method
	 * internally calls {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)}
	 * and passes the classname and the attributes mentioned in the class
	 * description as parameters
	 *
	 * @return a {@link java.util.List} of disk drive IDs
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#get(String, String)}
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
	public static List<String> getDriveID() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getID(classname, "DeviceID");
	}

	/**
	 * Fetches the Disk Details whose IDs were fetched using the
	 * {@link Win32_DiskDrive#getDriveID()} method
	 *
	 * @param driveID this parameter takes one Drive ID at a time from the drive ID
	 *                list
	 * @return a {@link java.util.Map} of Drive Details which contain the attributes
	 *         mentioned in the class description, as a key-value pair
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
	public static Map<String, String> getDrive(String driveID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getWhere(classname, "DeviceID", driveID, attributes);
	}
}
