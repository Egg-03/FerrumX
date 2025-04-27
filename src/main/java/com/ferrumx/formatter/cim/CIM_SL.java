package com.ferrumx.formatter.cim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ferrumx.exceptions.ShellException;

/**
 * This class queries all the WMI Classes based on the attributes passed to it's
 * one of the four methods called by the methods in other packages.
 * <p>
 * Supports Single-line parsing of output from the power-shell Is recommend for
 * extracting a single property only For multi-property extraction, see
 * {@link CIM_ML}
 *
 * @author Egg-03
 */
public class CIM_SL {

	private CIM_SL() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32class}
	 * | Select-Object {@literal property} | Format-List where the parameters
	 * are provided by the calling methods
	 *
	 * @param win32class the class name passed to by the calling method
	 * @param property   a single property requested for a particular class. The
	 *                   property requested by the calling methods can be found in
	 *                   their respective class descriptions
	 * @return the value of the property passed in the parameter
	 * @throws IOException               in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from
	 *                                   power-shell
	 * @throws ShellException            if any internal command used in the
	 *                                   power-shell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	public static String getPropertyValue(String win32class, String property)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32class + " | Select-Object " + property + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return dataCapture(process);
	}

	/**
	 * Internally runs the command "Get-CimInstance -ClassName {@literal win32class}
	 * | Where-Object {$_.{@literal determinantProperty} -eq {@literal determinantValue} |Select-Object
	 * {@literal extractProperty} | Format-List where the parameters are provided by
	 * the calling methods
	 *
	 * @param win32class          the class name passed to by the calling method
	 * @param determinantProperty a filtering parameter, passed to by the calling
	 *                            method
	 * @param determinantValue    the value of the filtering parameter, also passed
	 *                            to by the calling method
	 * @param extractProperty     a single property requested for a particular
	 *                            class. The property requested by the calling
	 *                            methods can be found in their respective class
	 *                            descriptions
	 * @return a {@link java.lang.String} of the attribute value requested by the
	 *         calling method
	 * @throws IOException               in case of general I/O errors
	 * @throws IndexOutOfBoundsException in case of text parsing issues from
	 *                                   power-shell
	 * @throws ShellException            if any internal command used in the
	 *                                   power-shell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	public static String getPropertyValueWhere(String win32class, String determinantProperty, String determinantValue,
			String extractProperty)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32class + " | Where-Object {$_." + determinantProperty + " -eq "
						+ "'" + determinantValue + "'}" + " | Select-Object " + extractProperty + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			errorCapture(process, exitCode);
		}

		return dataCapture(process);
	}

	/**
	 * Captures power-shell errors and throw them as ShellExceptions in case the
	 * process exit code is not 0
	 * 
	 * @param process
	 * @param exitCode
	 * @throws IOException
	 * @throws ShellException
	 */
	private static void errorCapture(Process process, int exitCode) throws IOException, ShellException {
		try (BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}
			throw new ShellException((errorLines.toString() + "\nProcess Exited with code:" + exitCode + "\n"));
		}
	}

	/**
	 * Captures the power-shell outputs and returns them in a String format
	 * 
	 * @param process
	 * @return
	 * @throws IOException
	 */
	private static String dataCapture(Process process) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));) {
			String currentLine;
			String actualName = "";

			while ((currentLine = br.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					if (currentLine.contains(" : ")) {
						actualName = currentLine;
					} else {
						actualName = actualName.concat(currentLine);
					}
				}
			}
			return actualName.substring(actualName.indexOf(":") + 1).strip();
		}
	}
}
