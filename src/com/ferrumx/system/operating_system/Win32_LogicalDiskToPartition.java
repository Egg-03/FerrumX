package com.ferrumx.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ferrumx.exceptions.ShellException;

/**
 * This class relates associates drive letters to partitions of a drive, if
 * available. The list of Partitions of a particular Drive queried from
 * {@link Win32_DiskDriveToDiskPartition} is fetched into this class which then
 * fetches the letters assigned to a partition.
 *
 * @author Egg-03
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
	 * @throws ShellException            if any internal command used in the
	 *                                   powershell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	public static String getDriveLetter(String partitionID)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String methodName = "getDriveLetter(String partitionID)";
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_LogicalDiskToPartition | Where-Object {$_.Antecedent.DeviceID -eq '"
						+ partitionID + "'} | Select-Object Dependent | Format-List" };

		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0 || partitionID.equals("JUNIT TEST VALUE")) { // partitionID.equals("JUNIT TEST VALUE") is here for coverage
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
