package com.ferrumx.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ferrumx.system.logger.ErrorLog;

/**
 * This class relates {@link com.ferrumx.system.hardware.Win32_DiskDrive} with
 * {@link Win32_LogicalDiskToPartition}.
 * <p>
 * The DeviceID of the Disk Drive queried from
 * {@link com.ferrumx.system.hardware.Win32_DiskDrive} is fetched into this
 * class which then gets all the partition list for the selected Drive. This
 * list can be then fetched to
 * {@link Win32_LogicalDiskToPartition#getDriveLetter(String)} to output the
 * drive letters for the given partitions in a drive
 *
 * @author Egg-03
 * @version 1.1.0
 */
public class Win32_DiskDriveToDiskPartition {
	private static String classname = "Win32_DiskDriveToDiskPartition";

	private Win32_DiskDriveToDiskPartition() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches a list of partitions for a particular drive
	 *
	 * @param driveID the DeviceID of the drive that is fetched form
	 *                {@link com.ferrumx.system.hardware.Win32_DiskDrive#getDriveID()}
	 * @return a {@link java.util.List} of partitions for a particular drive
	 * @throws IOException               in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from
	 *                                   powershell
	 */
	public static List<String> getPartitionList(String driveID) throws IOException, IndexOutOfBoundsException {
		String methodName = "getPartitionList(String driveID)";
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_DiskDriveToDiskPartition | Where-Object {$_.Antecedent.DeviceID -eq '"
						+ driveID + "'} | Select-Object Dependent | Format-List" };

		List<String> partitionList = new ArrayList<>();

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
				return Collections.emptyList();
			}
		} catch (InterruptedException e) {
			ErrorLog errorLog = new ErrorLog();
			errorLog.log("\n" + classname + "-" + methodName + "\n" + e.getMessage() + "\n\n");
			Thread.currentThread().interrupt();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;

		String value = "";
		while ((currentLine = br.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				if (currentLine.contains(" : ")) {
					partitionList.add(value = currentLine.substring(currentLine.indexOf("\"") + 1,
							currentLine.lastIndexOf("\"")));
				} else {
					int lastIndex = partitionList.size() - 1;
					partitionList.set(lastIndex, partitionList.get(lastIndex).concat(value));
				}
			}
		}
		br.close();

		return partitionList;
	}
}
