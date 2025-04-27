package com.ferrumx.system.hardware;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.formatter.cim.CIM_ML;
import com.ferrumx.formatter.cim.CIM_SL;

/**
 * Hardware ID generation class based on the SHA256 digest of the ID in the following format :
 * "CPUID+BaseboardID+DiskIDs"
 *
 * @author Egg-03
 */
public class HardwareID {
	
	private HardwareID() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Uses
	 * {@link com.ferrumx.formatter.cim.CIM_ML#getPropertyValueWhere(String, String, String, String)}
	 * to fetch IDE and SCSI Interface Type Disk IDs
	 *
	 * @return a concatenated list of all IDE and SCSI drive IDs currently installed
	 * @throws IndexOutOfBoundsException if there is a parsing error incurred during
	 *                                   extracting the IDs
	 * @throws IOException               in case of any IOException thrown by the
	 *                                   underlying parser
	 * @throws ShellException            if any internal command used in the
	 *                                   power-shell throws errors
	 * @throws InterruptedException      if the thread waiting for the process to
	 *                                   exit, gets interrupted. When catching this
	 *                                   exception, you may re-throw it's
	 *                                   interrupted status by using
	 *                                   Thread.currentThread().interrupt();
	 */
	private static String getDiskSerials() throws IndexOutOfBoundsException, IOException, ShellException, InterruptedException {
		List<String> ideInterface = CIM_ML.getPropertyValueWhere("Win32_DiskDrive", "InterfaceType", "IDE", "SerialNumber");
		List<String> scsiInterface = CIM_ML.getPropertyValueWhere("Win32_DiskDrive", "InterfaceType", "SCSI", "SerialNumber");

		StringBuilder ideDrives = new StringBuilder();
		StringBuilder scsiDrives = new StringBuilder();

		for (String ide : ideInterface) {
			ideDrives.append(ide);
		}

		for (String scsi : scsiInterface) {
			scsiDrives.append(scsi);
		}

		return ideDrives.toString() + scsiDrives.toString();
	}
	

	/**
	 * Uses {@link java.util.concurrent.ExecutorService} to spawn threads as needed with
	 * each thread calling the
	 * {@link com.ferrumx.formatter.cim.CIM_SL#getPropertyValue(String, String)} directly or
	 * through the Win32 Classes to get specific parts of HWID which is then
	 * ultimately combined to form the final ID
	 *
	 * @return the HWID of type {@link java.lang.String} in the format shown in the
	 *         class description
	 * @throws ExecutionException   when the underlying functions defined in
	 *                              {@link java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)}
	 *                              throw an exception
	 * @throws InterruptedException when any of the threads get interrupted
	 */
	public static String getHardwareID() throws ExecutionException, InterruptedException {

		List<String> id = new ArrayList<>();

		try (ExecutorService EXEC = Executors.newCachedThreadPool();) {
			
			Future<String> cpuIdTask = EXEC.submit(() -> CIM_SL.getPropertyValue("Win32_Processor", "ProcessorID"));
			Future<String> mainboardTask = EXEC.submit(()-> CIM_SL.getPropertyValue("Win32_Baseboard", "SerialNumber"));
			Future<String> driveIdTask = EXEC.submit(HardwareID::getDiskSerials);

			id.add(cpuIdTask.get());
			id.add(mainboardTask.get());
			id.add(driveIdTask.get());
			
			id.removeIf(s -> s == null || StringUtils.isBlank(s));
		}
		
	    return DigestUtils.sha256Hex(StringUtils.join(id, null).getBytes(StandardCharsets.UTF_8)).toUpperCase();
	}
}
