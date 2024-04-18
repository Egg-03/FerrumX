package com.ferrumx.tests;

import com.ferrumx.system.currentuser.User;

public class UserTest {

	public static void main(String[] args) {
		System.out.println(User.getUsername());
		System.out.println(User.getDirectory());
		System.out.println(User.getHome());
	}

}
