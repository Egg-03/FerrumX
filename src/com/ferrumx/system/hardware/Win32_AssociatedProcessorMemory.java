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
	 */
	public static List<String> getCacheID(String cpuID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_AssociatedProcessorMemory | Where-Object {$_.Dependent.DeviceID -eq '"
						+ cpuID + "'} | Select-Object Antecedent | Format-List" };

		

		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0 || cpuID.equals("JUNIT TEST VALUE")) { // cpuID.equals("JUNIT TEST VALUE") is here for coverage
			errorCapture(process, exitCode);
		}
		return dataCapture(process);
	}
	
	private static void errorCapture(Process process, int exitCode) throws IOException, ShellException {
		
		try(BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}
			throw new ShellException(errorLines.toString()+ "\nProcess Exited with code:" + exitCode + "\n");
		}
	}
	
	private static List<String> dataCapture(Process process) throws IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

			List<String> cacheIDList = new ArrayList<>();
			String currentLine;
			String value = "";
			while ((currentLine = br.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					if (currentLine.contains(" : ")) {
						value = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\""));
						cacheIDList.add(value);
					} else {
						int lastIndex = cacheIDList.size() - 1;
						cacheIDList.set(lastIndex, cacheIDList.get(lastIndex).concat(value));
					}
				}
			}
			return cacheIDList;
		}
	}
}
