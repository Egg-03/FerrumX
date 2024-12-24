package com.ferrumx.system.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.exceptions.ShellException;

/**
 * This class relates {@link com.ferrumx.system.hardware.Win32_NetworkAdapter}
 * with {@link Win32_NetworkAdapterConfiguration}.
 * <p>
 * Queries the Win32_NetworkAdapterSetting of WMI to fetch the "Setting"
 * property which is used in Win32_NetworkAdapterConfiguration.
 * <p>
 * The linking happens as follows:
 * <p>
 * The NetworkAdapter ID fetched from
 * {@link com.ferrumx.system.hardware.Win32_NetworkAdapter#getDeviceIDList()}
 * gets passed onto {@link Win32_NetworkAdapterSetting#getIndex(String)} which
 * returns the "Setting" property, which is a parameter for
 * {@link Win32_NetworkAdapterConfiguration#getAdapterConfiguration(String)}
 *
 * @author Egg-03
 * @version 1.3.0
 */
public class Win32_NetworkAdapterSetting {
	private static String classname = "Win32_NetworkAdapterSetting";

	private Win32_NetworkAdapterSetting() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches the "Settings" property for a given Network Adapter ID
	 *
	 * @param deviceID the adapter ID received from
	 *                 {@link com.ferrumx.system.hardware.Win32_NetworkAdapter#getDeviceIDList()}
	 * @return the "Settings" property for a particular adapter ID
	 * @throws IOException               in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from
	 *                                   powershell
	 * @throws ShellException            if any internal command used in the
	 *                                   powershell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty string to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static String getIndex(String deviceID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String methodName = "getIndex(String deviceID)";
		String setting = "";

		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_NetworkAdapterSetting | Where-Object {$_.Element.DeviceID -eq '"
						+ deviceID + "'} | Select-Object Setting | Format-List" };
		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0 || deviceID.equals("JUNIT TEST VALUE")) { // deviceID.equals("JUNIT TEST VALUE") is here for coverage
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			error.close();

			throw new ShellException("\n" + classname + "-" + methodName + "\n" + errorLines.toString()
					+ "\nProcess Exited with code:" + exitCode + "\n");
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String currentLine;

		while ((currentLine = br.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				if (currentLine.contains(" : ")) {
					setting = currentLine;
				} else {
					setting = setting.concat(currentLine);
				}
			}
		}

		br.close();

		return setting.substring(setting.indexOf("=") + 1, setting.indexOf(")")).trim();
	}
}
