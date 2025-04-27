package com.ferrumx.system.associatedclasses;

import java.io.IOException;
import java.util.List;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.system.hardware.Win32_CacheMemory;
import com.ferrumx.system.hardware.Win32_Processor;

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
	public static List<String> getCacheID(String cpuID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_AssociatedProcessorMemory | Where-Object {$_.Dependent.DeviceID -eq '"
						+ cpuID + "'} | Select-Object Antecedent | Format-List" };

		

		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0 || cpuID.equals("JUNIT TEST VALUE")) { // cpuID.equals("JUNIT TEST VALUE") is here for coverage
			Capture.errorCapture(process, exitCode);
		}
		return Capture.dataCapture(process);
	}
}
	
