package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;

/**
 * Hardware ID generation class based on the following format : "CPUName/CPUID/MotherboardName/DriveIDs"
 * @author Egg-03
 * @version 1.2.4
 */
public class HardwareID {
	protected HardwareID() {
		throw new IllegalStateException("Utility Class");
	}
	/**
	 * Uses {@link com.ferrumx.formatter.cim.CIM_ML#getIDWhere(String, String, String, String)} to fetch IDE and SCSI Interface Type Disk IDs
	 * @return a concatenated list of all IDE and SCSI drive IDs currently installed
	 * @throws IndexOutOfBoundsException if there is a parsing error incurred during extracting the IDs
	 * @throws IOException in case of any IOException thrown by the underlying parser
	 */
	private static String getDiskSerials() throws IndexOutOfBoundsException, IOException {
		List<String> ideInterface = CIM_ML.getIDWhere("Win32_DiskDrive", "InterfaceType", "IDE", "SerialNumber");
		List<String> scsiInterface = CIM_ML.getIDWhere("Win32_DiskDrive", "InterfaceType", "SCSI", "SerialNumber");
		
		StringBuilder ideDrives = new StringBuilder("");
		StringBuilder scsiDrives = new StringBuilder("");
		
		for(String ide:ideInterface)
			ideDrives.append(ide);
		
		for(String scsi:scsiInterface)
			scsiDrives.append(scsi);
		
		return ideDrives.toString()+scsiDrives.toString();
	}
	
	/**
	 * Uses {@link java.util.concurrent.ExecutorService} to spawn seven threads with each thread calling the {@link com.ferrumx.formatter.cim.CIM_SL#get(String, String)} directly or through the Win32 Classes to get specific parts of HWID which is then ultimately combined to form the final ID
	 * @return the HWID of type {@link java.lang.String} in the format shown in the class description
	 * @throws ExecutionException when the underlying functions defined in {@link java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)} throw an exception
	 * @throws InterruptedException when any of the threads get interrupted
	 */
	public static String getHardwareID() throws ExecutionException, InterruptedException {
		
		String cpuName = "N/A";
		String cpuId = "N/A";
		String motherBoardName = "N/A";
		String driveId = "N/A";

		try(ExecutorService EXEC = Executors.newFixedThreadPool(4);) {
			Future<String> cpuNameTask = EXEC.submit(()-> CIM_SL.get("Win32_Processor", "Name"));
			Future<String> cpuIdTask = EXEC.submit(()-> CIM_SL.get("Win32_Processor", "ProcessorID"));
			Future<String> motherBoardNameTask = EXEC.submit(()-> CIM_SL.get("Win32_BaseBoard", "Product"));
			Future<String> driveIdTask = EXEC.submit(HardwareID::getDiskSerials);
			
			cpuName = cpuNameTask.get();
			cpuId = cpuIdTask.get();
			motherBoardName = motherBoardNameTask.get();
			driveId = driveIdTask.get();	
		}
		return cpuName+"/"+cpuId+"/"+motherBoardName+"/"+driveId;
	}
}
