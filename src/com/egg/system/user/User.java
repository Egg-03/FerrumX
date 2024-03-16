package com.egg.system.user;

public class User {
	public static String getUsername() {
		return System.getProperty("user.name");
	}
	
	public static String getHome() {
		return System.getProperty("user.home");
	}
	
	public static String getDirectory() {
		return System.getProperty("user.dir");
	}
}
