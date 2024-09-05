package com.ferrumx.tests;

import java.util.concurrent.ExecutionException;

import com.ferrumx.system.hardware.HardwareID;

public class HWIDTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println(HardwareID.getHardwareID());
	}

}
