package com.ferrumx.system.operating_system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty List to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static List<String> getOSList() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertyValue(classname, "Name");
	}

	/**
	 * Fetches a OS properties for a particular OS
	 *
	 * @param OSName the iterative List fetched from {@link #getOSList()}
	 * @return a {@link java.util.Map} of OS properties and their values mentioned
	 *         in the class description
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty Map to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static Map<String, String> getOSInfo(String OSName) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.getPropertiesAndTheirValuesWhere(classname, "Name", OSName, attributes);
	}
}
