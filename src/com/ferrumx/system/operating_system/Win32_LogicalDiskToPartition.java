package com.ferrumx.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ferrumx.system.logger.ErrorLog;

/**
 * This class relates associates drive letters to partitions of a drive, if
 * available. The list of Partitions of a particular Drive queried from
 * {@link Win32_DiskDriveToDiskPartition} is fetched into this class which then
 * fetches the letters assigned to a partition.
 *
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_LogicalDiskToPartition {
	private static String classname = "Win32_LogicalDiskToPartition";

	private Win32_LogicalDiskToPartition() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Assigns partition letters to drive partitions
	 *
	 * @param partitionID the partition of a drive, fetched from
	 *                    {@link Win32_DiskDriveToDiskPartition#getPartitionList(String)}
	 * @return the drive letter associated with the current partition. If no letters
	 *         are assigned, it returns "N/A"
	 * @throws IOException               in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from
	 *                                   powershell
	 */
	public static String getDriveLetter(String partitionID) throws IOException, IndexOutOfBoundsException {
		String methodName = "getDriveLetter(String partitionID)";
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_LogicalDiskToPartition | Where-Object {$_.Antecedent.DeviceID -eq '"
						+ partitionID + "'} | Select-Object Dependent | Format-List" };

		Process process = Runtime.getRuntime().exec(command);
		try {
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String errorLine;
				List<String> errorList = new ArrayList<>();

				while ((errorLine = error.readLine()) != null) {
					if (!errorLine.isBlank() || !errorLine.isEmpty()) {
						errorList.add(errorLine);
					}
				}

				error.close();
				ErrorLog errorLog = new ErrorLog();

				errorLog.log("\n" + classname + "-" + methodName + "\n" + errorList.toString()
						+ "\nProcess Exited with code:" + exitCode + "\n");
				Collections.emptyList();
			}
		} catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n" + classname + "-" + methodName + "\n" + e.getMessage() + "\n\n");
			Thread.currentThread().interrupt();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		String driveLetter = "N/A";
		while ((currentLine = br.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				driveLetter = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\""));
			}
		}
		br.close();

		return driveLetter;
	}
}
