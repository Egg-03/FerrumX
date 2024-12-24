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
 * @version 1.3.0
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty list to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static List<String> getID(String win32Class, String Key)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Select-Object " + Key + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			error.close();

			throw new ShellException("\n" + win32Class + "-" + Key + "\n" + errorLines.toString()
					+ "\nProcess Exited with code:" + exitCode + "\n\n");

		}

		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));

		List<String> id = new ArrayList<>();
		String currentLine;
		String value = "";
		while ((currentLine = stream.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				if (currentLine.contains(" : ")) {
					id.add(value = currentLine);
				} else {
					int lastIndex = id.size() - 1;
					id.set(lastIndex, id.get(lastIndex).concat(value));
				}
			}
		}

		stream.close();

		// strip the property_name and keep only the property value
		for (int i = 0; i < id.size(); i++) {
			id.set(i, id.get(i).substring(id.get(i).indexOf(":") + 1).strip());
		}
		stream.close();
		return id;
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty List to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static List<String> getIDWhere(String win32Class, String determinantProperty, String determinantValue,
			String extractProperty)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Where-Object {$_." + determinantProperty + " -eq "
						+ "'" + determinantValue + "'}" + " | Select-Object " + extractProperty + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			error.close();

			throw new ShellException("\n" + win32Class + "-" + extractProperty + "\n" + errorLines.toString()
					+ "\nProcess Exited with code:" + exitCode + "\n\n");
		}

		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));

		List<String> id = new ArrayList<>();
		String currentLine;
		String value = "";
		while ((currentLine = stream.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				if (currentLine.contains(" : ")) {
					id.add(value = currentLine);
				} else {
					int lastIndex = id.size() - 1;
					id.set(lastIndex, id.get(lastIndex).concat(value));
				}
			}
		}

		stream.close();

		// strip the property_name and keep only the property value
		for (int i = 0; i < id.size(); i++) {
			id.set(i, id.get(i).substring(id.get(i).indexOf(":") + 1).strip());
		}
		stream.close();
		return id;
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty Map to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static Map<String, String> get(String win32Class, String attributes)
			throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Select-Object " + attributes + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			error.close();

			throw new ShellException("\n" + win32Class + "-" + attributes + "\n" + errorLines.toString()
					+ "\nProcess Exited with code:" + exitCode + "\n\n");
		}

		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();

		while ((currentLine = stream.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if (currentLine.contains(" : ")) {
					propertyValues.put(key = currentLine.substring(0, currentLine.indexOf(":")).strip(),
							value = currentLine.substring(currentLine.indexOf(":") + 1).strip());
				} else {
					propertyValues.replace(key, value.concat(currentLine.strip()));
				}
			}
		}
		stream.close();
		return propertyValues;
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
	 *                                   <p>
	 *                                   While catching any of the Exceptions, you
	 *                                   may return an empty Map to avoid any
	 *                                   {@link java.lang.NullPointerException} that
	 *                                   might get thrown because your variable
	 *                                   might be expecting a string. However, this
	 *                                   does not make you immune from the
	 *                                   NullPointerExceptions that may be thrown in
	 *                                   case of powershell output format changes in
	 *                                   the future, causing the underlying parsing
	 *                                   logic to fail.
	 */
	public static Map<String, String> getWhere(String win32Class, String determinantProperty, String determinantValue,
			String attributes) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {

		String[] command = { "powershell.exe",
				"Get-CimInstance -ClassName " + win32Class + " | Where-Object {$_." + determinantProperty + " -eq "
						+ "'" + determinantValue + "'}" + " | Select-Object " + attributes + " | Format-List" };
		Process process = Runtime.getRuntime().exec(command);
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			error.close();

			throw new ShellException("\n" + win32Class + "-" + determinantProperty + "-" + determinantValue + "-"
					+ attributes + "\n" + errorLines.toString() + "\nProcess Exited with code:" + exitCode + "\n\n");
		}

		BufferedReader stream = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String currentLine;
		Map<String, String> propertyValues = new LinkedHashMap<>();

		while ((currentLine = stream.readLine()) != null) {
			if (!currentLine.isBlank() || !currentLine.isEmpty()) {
				String key = "";
				String value = "";
				if (currentLine.contains(" : ")) {
					propertyValues.put(key = currentLine.substring(0, currentLine.indexOf(":")).strip(),
							value = currentLine.substring(currentLine.indexOf(":") + 1).strip());
				} else {
					propertyValues.replace(key, value.concat(currentLine.strip()));
				}
			}
		}
		stream.close();
		return propertyValues;
	}
}
