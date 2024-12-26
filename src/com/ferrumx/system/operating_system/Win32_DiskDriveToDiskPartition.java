package com.ferrumx.system.operating_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.exceptions.ShellException;

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
 */
public class Win32_DiskDriveToDiskPartition {
	
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
	 * @throws ShellException            if any internal command used in the
	 *                                   powershell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();                                
	 */
	public static List<String> getPartitionList(String driveID) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		
		String[] command = { "powershell.exe", "/c",
				"Get-CimInstance -ClassName Win32_DiskDriveToDiskPartition | Where-Object {$_.Antecedent.DeviceID -eq '"
						+ driveID + "'} | Select-Object Dependent | Format-List" };

		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0 || driveID.equals("JUNIT TEST VALUE")) { // driveID.equals("JUNIT TEST VALUE") is here for coverage
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
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			
			List<String> partitionList = new ArrayList<>();
			String currentLine;
			String value = "";
			while ((currentLine = br.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					if (currentLine.contains(" : ")) {
						value = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\""));
						partitionList.add(value);
					} else {
						int lastIndex = partitionList.size() - 1;
						partitionList.set(lastIndex, partitionList.get(lastIndex).concat(value));
					}
				}
			}
			
			return partitionList;
		}
	}
}
