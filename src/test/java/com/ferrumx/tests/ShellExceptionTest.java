package com.ferrumx.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;
import com.ferrumx.system.associatedclasses.Win32_AssociatedProcessorMemory;
import com.ferrumx.system.associatedclasses.Win32_DiskDriveToDiskPartition;
import com.ferrumx.system.associatedclasses.Win32_LogicalDiskToPartition;
import com.ferrumx.system.associatedclasses.Win32_NetworkAdapterSetting;
/**
 * This will query the power-shell with wrong commands to see if the error received is thrown as a ShellException
 */
class ShellExceptionTest {

	@Test
	void slGet() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_SL.getPropertyValue("FalseClass", "FalseAttribute");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		} 
	}
	
	@Test
	void slgetWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_SL.getPropertyValueWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		} 
	}
	
	@Test
	void mlget() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getPropertiesAndTheirValues("Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetId() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getPropertyValue("Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetIdWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getPropertyValueWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void mlgetWhere() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			CIM_ML.getPropertiesAndTheirValuesWhere("Test", "Test", "Test", "Test");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void associatedProcessorMemoryErrorTest() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			Win32_AssociatedProcessorMemory.getCacheID("JUNIT TEST VALUE");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void diskDriveToPartitionErrorTest() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			Win32_DiskDriveToDiskPartition.getPartitionList("JUNIT TEST VALUE");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void logicalDiskToPartitionErrorTest() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			Win32_LogicalDiskToPartition.getDriveLetter("JUNIT TEST VALUE");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}
	
	@Test
	void networkAdapterSettingErrorTest() throws IndexOutOfBoundsException, IOException, InterruptedException {
		try {
			Win32_NetworkAdapterSetting.getIndex("JUNIT TEST VALUE");
			fail("Expected exception was not thrown");
		} catch (ShellException e) {
			Logger.debug(e.getMessage());
		}
	}


}
