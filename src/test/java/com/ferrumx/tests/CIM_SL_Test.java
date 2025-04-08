package com.ferrumx.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_SL;

/**
 * CIM_SL class isn't used much by the calling methods in the provided classes, so a test has been provided for its methods
 */
class CIM_SL_Test {

	@Test
	void slGet() throws IndexOutOfBoundsException, IOException, ShellException, InterruptedException {
		String test = CIM_SL.get("Win32_Baseboard", "Manufacturer");
		Logger.debug(test);
		if(test.isBlank() || test.isEmpty()) {
			fail();
		}
	}
	
	@Test
	void slGetWhere() throws IndexOutOfBoundsException, IOException, ShellException, InterruptedException {
		String test = CIM_SL.getWhere("Win32_Processor", "DeviceID", "CPU0", "Name");
		Logger.debug(test);
		if(test.isBlank() || test.isEmpty()) {
			fail();
		}
	}

}
