package com.ferrumx.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;

/**
 * This class queries all the WMI Classes based on the attributes passed to it's
 * one of the four methods called by the methods in other packages.
 * <p>
 * Supports Multi-line parsing of output from the powershell Is recommended for
 * extracting multiple properties at once For single property extraction, see
 * {@link CIM_SL}
 *
 * @author Egg-03
 */
public class CIM_ML {
	private CIM_ML() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class}
	 * | Select-Object {@literal Key} | Format-List where the parameters are
	 * provided by the calling methods
	 *
	 * @param win32Class the classname passed to by the method calling it
	 * @param Key        passed to by the method calling it
	 * @return a list of values requested by the method calling it. The values
	 *         returned are of the property {@literal Key}
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
	public static List<String> getID(String win32Class, String Key) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Select-Object " + Key + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return attributeValues(process);
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class}
	 * | Where-Object {$_.{@literal determinantProperty} -eq
	 * {@literal determinantValue}} | Select-Object {@literal extractProperty} |
	 * Format-List where the parameters are provided by the calling methods
	 *
	 * @param win32Class          the classname passed to by the calling method
	 * @param determinantProperty a filtering parameter, passed to by the calling
	 *                            method
	 * @param determinantValue    the value of the filtering parameter, also passed
	 *                            to by the calling method
	 * @param extractProperty     the property to be extracted, provided by the
	 *                            calling method.
	 * @return a list of values requested by the method calling it. The values
	 *         returned are the values of the property {@literal extractProperty}
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
	public static List<String> getIDWhere(String win32Class, String determinantProperty, String determinantValue, String extractProperty) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Where-Object {$_." + determinantProperty + " -eq "
						+ "'" + determinantValue + "'}" + " | Select-Object " + extractProperty + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return attributeValues(process);
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class}
	 * | Select-Object {@literal attributes} | Format-List where the parameters are
	 * provided by the calling methods
	 *
	 * @param win32Class the classname passed to by the calling method
	 * @param attributes a list of properties requested for a particular class. The
	 *                   properties requested by the calling methods can be found in
	 *                   their respective class descriptions
	 * @return a {@link java.util.Map} of the attribute values requested by the
	 *         calling method
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
	public static Map<String, String> get(String win32Class, String attributes)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Select-Object " + attributes + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return attributesAndTheirValues(process);
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32Class}
	 * | Where-Object {$_.{@literal determinantProperty} -eq
	 * {@literal determinantValue}} | Select-Object {@literal extractProperty} |
	 * Format-List where the parameters are provided by the calling methods
	 *
	 * @param win32Class          win32Class the classname passed to by the calling
	 *                            method
	 * @param determinantProperty a filtering parameter, passed to by the calling
	 *                            method
	 * @param determinantValue    the value of the filtering parameter, also passed
	 *                            to by the calling method
	 * @param attributes          a list of properties requested for a particular
	 *                            class. The properties requested by the calling
	 *                            methods can be found in their respective class
	 *                            descriptions
	 * @return a {@link java.util.Map} of the attribute values requested by the
	 *         calling method
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
	
	public static Map<String, String> getWhere(String win32Class, String determinantProperty, String determinantValue,String attributes) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Where-Object {$_." + determinantProperty + " -eq "
						+ "'" + determinantValue + "'}" + " | Select-Object " + attributes + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return attributesAndTheirValues(process);
	}
	
	/**
	 * Captures power-shell errors and throw them as ShellExceptions in case the process exit code is not 0
	 * @param process
	 * @param exitCode
	 * @throws IOException
	 * @throws ShellException
	 */
	private static void errorCapture(Process process, int exitCode) throws IOException, ShellException {
		try(BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}
			throw new ShellException ((errorLines.toString()+ "\nProcess Exited with code:" + exitCode + "\n"));
		}
	}
	
	/**
	 * Returns a list of attribute values, leaving the attribute names behind
	 * @param process
	 * @return
	 * @throws IOException
	 */
	private static List<String> attributeValues(Process process) throws IOException {
		try(BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			List<String> attributeValues = new ArrayList<>();
			String currentLine;
			String value = "";
			while ((currentLine = stream.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					if (currentLine.contains(" : ")) {
						value = currentLine;
						attributeValues.add(currentLine);
					} else {
						int lastIndex = attributeValues.size() - 1;
						attributeValues.set(lastIndex, attributeValues.get(lastIndex).concat(value));
					}
				}
			}

			// strip the attribute name and keep only the attribute value
			for (int i = 0; i < attributeValues.size(); i++) {
				attributeValues.set(i, attributeValues.get(i).substring(attributeValues.get(i).indexOf(":") + 1).strip());
			}
			return attributeValues;
		}
	}
	
	/**
	 * Returns a map of attribute names and their values as a key-value pair with attribute name being the key and the attribute value being the value
	 * @param process
	 * @return
	 * @throws IOException
	 */
	private static Map<String, String> attributesAndTheirValues(Process process) throws IOException {
		try(BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String currentLine;
			Map<String, String> attributesAndTheirValues = new LinkedHashMap<>();

			while ((currentLine = stream.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					String key = "";
					String value = "";
					if (currentLine.contains(" : ")) {
						key = currentLine.substring(0, currentLine.indexOf(":")).strip();
						value = currentLine.substring(currentLine.indexOf(":") + 1).strip();
						attributesAndTheirValues.put(key, value);
					} else {
						attributesAndTheirValues.replace(key, value.concat(currentLine.strip()));
					}
				}
			}
			return attributesAndTheirValues;
		}
	}
}
