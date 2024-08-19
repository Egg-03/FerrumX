package com.ferrumx.system.operating_system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

/**
 * This class queries the Win32_OperatingSystem class of WMI and fetches the
 * current OS details.
 * <p>
 * The following properties are fetched: Caption, InstallDate, CSName,
 * LastBootUpTime, LocalDateTime, Distributed, NumberOfUsers, Version,
 * BootDevice, BuildNumber, BuildType, Manufacturer, OSArchitecture,
 * MUILanguages, PortableOperatingSystem, Primary, RegisteredUser, SerialNumber,
 * ServicePackMajorVersion, ServicePackMinorVersion, SystemDirectory,
 * SystemDrive, WindowsDirectory
 *
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_OperatingSystem {
	private static String classname = "Win32_OperatingSystem";
	private static String attributes = "Caption, InstallDate, CSName, LastBootUpTime, LocalDateTime, Distributed, NumberOfUsers, Version, BootDevice, BuildNumber, BuildType, Manufacturer, OSArchitecture, MUILanguages, PortableOperatingSystem, Primary, RegisteredUser, SerialNumber, ServicePackMajorVersion, ServicePackMinorVersion, SystemDirectory, SystemDrive, WindowsDirectory";

	private Win32_OperatingSystem() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches a list of OS Names installed in the System. In case of Multi-Boot
	 * systems, more than one OS names may appear
	 *
	 * @return a {@link java.util.List} of OS Names found in the system
	 * @throws IOException               re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
	 *                                   when there are I/O Errors during streaming
	 *                                   of data from and to Powershell and other
	 *                                   generated files
	 * @throws IndexOutOfBoundsException re-throws the exception thrown by
	 *                                   {@link com.ferrumx.formatter.cim.CIM_ML#getID(String, String)}
	 *                                   when there is a parsing error of data
	 *                                   fetched from Windows Powershell
	 */
	public static List<String> getOSList() throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getID(classname, "Name");
	}

	/**
	 * Fetches a OS properties for a particular OS
	 *
	 * @param OSName the iterative List fetched from {@link #getOSInfo(String)}
	 * @return a {@link java.util.Map} of OS properties and their values mentioned
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
	public static Map<String, String> getOSInfo(String OSName) throws IOException, IndexOutOfBoundsException {
		return CIM_ML.getWhere(classname, "Name", OSName, attributes);
	}
}
