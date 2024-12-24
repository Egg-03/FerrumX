package com.ferrumx.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;
/**
 * This will query the power-shell with wrong commands to see if the error received is thrown as a ShellException
 */
class ShellExceptionTest {

	@Test
	void slGet() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_SL.get("FalseClass", "FalseATtribute");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		} 
	}
	
	@Test
	void slgetWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_SL.getWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		} 
	}
	
	@Test
	void mlget() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.get("Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetId() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getID("Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetIdWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getIDWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}

}
