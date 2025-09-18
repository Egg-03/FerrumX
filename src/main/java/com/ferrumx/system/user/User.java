package com.ferrumx.system.user;

import org.jetbrains.annotations.NotNull;

/**
 * Retrieves the User Information of the Account logged in to the system
 */
public class User {
	private User() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Fetches the user-name of the account currently logged in
	 *
	 * @return the user-name in {@link java.lang.String} format
	 */
    @NotNull
	public static String getUsername() {
		return System.getProperty("user.name");
	}

	/**
	 * Fetches the User directory in the form: "C:/Users/User-Name
	 *
	 * @return the User directory in {@link java.lang.String} format
	 */
    @NotNull
	public static String getHome() {
		return System.getProperty("user.home");
	}

	/**
	 * Fetches the current working directory.
	 *
	 * @return the current working directory in {@link java.lang.String} format
	 */
    @NotNull
	public static String getDirectory() {
		return System.getProperty("user.dir");
	}
}
