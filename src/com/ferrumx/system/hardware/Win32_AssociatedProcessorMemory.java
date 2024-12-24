package com.ferrumx.system.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.exceptions.ShellException;

/**
 * This class serves as a relationship between {@link Win32_Processor} and
 * {@link Win32_CacheMemory}. This class will fetch the CacheMemory IDs (L1, L2
 * and L3 ID) of a CPU based on the CPU ID given by Win32_Processor. The cache
 * ID's extracted from this class will then be used in Win32_CacheMemory to
 * retrieve cache related information
 *
 * @author Egg-03
 */

public class Win32_AssociatedProcessorMemory {
	private static String classname = "Win32_AssociatedProcessorMemory";

	private Win32_AssociatedProcessorMemory() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches a list of cache IDs for a particular CPU
	 *
	 * @param cpuID The DeviceID property returned by {@link Win32_Processor}
	 * @return a {@link java.util.List} of cacheIDs
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
	public static List<String> getCacheID(String cpuID)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String methodName = "getCacheID(String cpuID)";
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_AssociatedProcessorMemory | Where-Object {$_.Dependent.DeviceID -eq '"
						+ cpuID + "'} | Select-Object Antecedent | Format-List" };

		List<String> cacheIDList = new ArrayList<>();

		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0 || cpuID.equals("JUNIT TEST VALUE")) { // cpuID.equals("JUNIT TEST VALUE") is here for coverage
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

		String value = "";
		while ((currentLine = br.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				if (currentLine.contains(" : ")) {
					cacheIDList.add(value = currentLine.substring(currentLine.indexOf("\"") + 1,
							currentLine.lastIndexOf("\"")));
				} else {
					int lastIndex = cacheIDList.size() - 1;
					cacheIDList.set(lastIndex, cacheIDList.get(lastIndex).concat(value));
				}
			}
		}

		br.close();

		return cacheIDList;
	}
}
