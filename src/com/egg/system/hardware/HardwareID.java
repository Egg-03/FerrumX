package com.egg.system.hardware;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.egg.formatter.cim.CIM;
import com.egg.system.currentuser.User;

public class HardwareID {
	private HardwareID() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getHardwareID() throws ExecutionException, InterruptedException {
		
		ExecutorService EXEC = Executors.newFixedThreadPool(7);
		
		Future<String> cpuNameTask = EXEC.submit(()-> CIM.getValues("Win32_Processor", "Name"));
		Future<String> cpuIdTask = EXEC.submit(()-> CIM.getValues("Win32_Processor", "ProcessorID"));
		Future<String> motherBoardNameTask = EXEC.submit(()-> CIM.getValues("Win32_BaseBoard", "Product"));
		Future<String> deviceNameTask = EXEC.submit(()-> CIM.getValues("Win32_OperatingSystem", "CSName"));
		Future<String> userNameTask = EXEC.submit(User::getUsername);
		Future<Integer> countRAMTask = EXEC.submit(()-> Win32_PhysicalMemory.getTag().size());
		Future<Integer> countStorageTask = EXEC.submit(()-> Win32_DiskDrive.getDriveID().size());
		
		String cpuName = cpuNameTask.get();
		String cpuId = cpuIdTask.get();
		String motherBoardName = motherBoardNameTask.get();
		String deviceName = deviceNameTask.get();
		String userName = userNameTask.get();
		int countRAM = countRAMTask.get();
		int countStorage = countStorageTask.get();
		
		EXEC.shutdown();
		return userName+"/"+deviceName+"/"+cpuName+"/"+cpuId+"/"+motherBoardName+"/"+countRAM+"/"+countStorage;
	}
}
