package com.egg.tests;

import com.egg.system.user.User;

public class UserTest {

	public static void main(String[] args) {
		System.out.println(User.getUsername());
		System.out.println(User.getDirectory());
		System.out.println(User.getHome());
	}

}
