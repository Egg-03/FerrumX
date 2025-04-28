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
		
		// load list with ide interface disk ids first
		List<String> allDiskSerials = CIM_ML.getPropertyValueWhere("Win32_DiskDrive", "InterfaceType", "IDE", "SerialNumber");
		// then add scsi interface disk ids to it
		allDiskSerials.addAll(CIM_ML.getPropertyValueWhere("Win32_DiskDrive", "InterfaceType", "SCSI", "SerialNumber"));
		
		return StringUtils.join(allDiskSerials, null);
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
			
			Future<String> cpuIdTask = EXEC.submit(() -> StringUtils.join(CIM_ML.getPropertyValue("Win32_Processor", "ProcessorID"), null));
			Future<String> mainboardTask = EXEC.submit(()-> StringUtils.join(CIM_ML.getPropertyValue("Win32_Baseboard", "SerialNumber"), null));
			Future<String> driveIdTask = EXEC.submit(HardwareID::getDiskSerials);

			id.add(cpuIdTask.get());
			id.add(mainboardTask.get());
			id.add(driveIdTask.get());
			
			id.removeIf(s -> s == null || StringUtils.isBlank(s));
		}
		
	    return DigestUtils.sha256Hex(StringUtils.join(id, null).getBytes(StandardCharsets.UTF_8)).toUpperCase();
	}
}
