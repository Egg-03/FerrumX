package com.ferrumx.system.associatedclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ferrumx.exceptions.ShellException;

/**
 * This class contains a set of methods that capture Power-shell errors and outputs
 * The methods of this class are used internally by various other methods and should not be called by the
 * users of this wrapper
 */
final class Capture {
	
	private Capture() {
		throw new IllegalStateException("Utility Class");
	}
	
	/**
	 * Captures the error with its code
	 * @param process The power-shell process
	 * @param exitCode The exit code returned by the power-shell upon completion
	 * @throws IOException in cases of general IO errors
	 * @throws ShellException throws the error captured as an customized exception
	 */
	protected static void errorCapture(Process process, int exitCode) throws IOException, ShellException {
		try (BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

			String errorLine;
			StringBuilder errorLines = new StringBuilder();

			while ((errorLine = error.readLine()) != null) {
				if (!errorLine.isBlank() || !errorLine.isEmpty()) {
					errorLines.append(errorLine);
				}
			}

			throw new ShellException(errorLines.toString() + "\nProcess Exited with code:" + exitCode + "\n");
		}
	}
	
	/**
	 * Captures the output of the Power-shell
	 * @param process The power-shell process
	 * @return the data in the form of a List<String>
	 * @throws IOException in case of general IO errors
	 */
	protected static List<String> dataCapture(Process process) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

			List<String> data = new ArrayList<>();
			String currentLine;
			String value = "";
			while ((currentLine = br.readLine()) != null) {
				if (!currentLine.isBlank() || !currentLine.isEmpty()) {
					if (currentLine.contains(" : ")) {
						value = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\""));
						data.add(value);
					} else {
						int lastIndex = data.size() - 1;
						data.set(lastIndex, data.get(lastIndex).concat(value));
					}
				}
			}
			return data;
		}
	}
}
