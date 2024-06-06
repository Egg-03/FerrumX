package com.ferrumx.system.hardware;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ferrumx.formatter.cim.CIM_SL;
import com.ferrumx.system.currentuser.User;

/**
 * Hardware ID generation class based on the following format : "User-name/DeviceName/CPU/CPUID/MotherboardName/RAM Count/Storage Count"
 * @author Egg-03
 * @version 1.1.0
 */
public class HardwareID {
	private HardwareID() {
		throw new IllegalStateException("Utility Class");
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
		String deviceName = "N/A";
		String userName = "N/A";
		int countRAM = 0;
		int countStorage = 0;

		
		try(ExecutorService EXEC = Executors.newFixedThreadPool(7);) {
			Future<String> cpuNameTask = EXEC.submit(()-> CIM_SL.get("Win32_Processor", "Name"));
			Future<String> cpuIdTask = EXEC.submit(()-> CIM_SL.get("Win32_Processor", "ProcessorID"));
			Future<String> motherBoardNameTask = EXEC.submit(()-> CIM_SL.get("Win32_BaseBoard", "Product"));
			Future<String> deviceNameTask = EXEC.submit(()-> CIM_SL.get("Win32_OperatingSystem", "CSName"));
			Future<String> userNameTask = EXEC.submit(User::getUsername);
			Future<Integer> countRAMTask = EXEC.submit(()-> Win32_PhysicalMemory.getTag().size());
			Future<Integer> countStorageTask = EXEC.submit(()-> Win32_DiskDrive.getDriveID().size());
			
			cpuName = cpuNameTask.get();
			cpuId = cpuIdTask.get();
			motherBoardName = motherBoardNameTask.get();
			deviceName = deviceNameTask.get();
			userName = userNameTask.get();
			countRAM = countRAMTask.get();
			countStorage = countStorageTask.get();
		}
		return userName+"/"+deviceName+"/"+cpuName+"/"+cpuId+"/"+motherBoardName+"/"+countRAM+"/"+countStorage;
	}
}
