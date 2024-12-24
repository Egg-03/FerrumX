package com.ferrumx.system.operating_system;

import java.io.IOException;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;

/**
 * Fetches the OS Time-Zone information by querying WIn32_TimeZone class of WMI.
 * <p>
 * The following attributes are fetched: Caption, Bias, StandardName
 *
 * @author Egg-03
 */
public class Win32_TimeZone {
	private static String classname = "Win32_TimeZone";
	private static String attributes = "Caption, Bias, StandardName";

	private Win32_TimeZone() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Retrieves the OS Time-zone
	 *
	 * @return a {@link java.util.Map} of Time-zone properties and values mentioned
	 *         in the class description
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
	public static Map<String, String> getOSTimeZone() throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		return CIM_ML.get(classname, attributes);
	}
}
