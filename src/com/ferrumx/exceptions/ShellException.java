package com.ferrumx.exceptions;

/**
 * This is a custom exception class that is thrown when there are errors from the powershell's output
 *
 * @author Egg-03
 * @version 1.2.5
 */
public class ShellException extends Exception{
	private static final long serialVersionUID = 5805343712198045667L;

	public ShellException(String message) {
		super(message);
	}
}
