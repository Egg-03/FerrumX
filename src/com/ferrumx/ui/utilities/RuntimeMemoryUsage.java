package com.ferrumx.ui.utilities;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;

public class RuntimeMemoryUsage {
	private static final long MEGABYTE = 1024L * 1024L;
	
	private RuntimeMemoryUsage() {
		throw new IllegalStateException("Utility Class");
	}
	
	private static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}
	
	public static void memoryUsage(JTextField memory) {
		new Thread(()->{
			while(true) {
				MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
				MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
				long usedHeapMemory = heapMemoryUsage.getUsed();
				
				memory.setText(bytesToMegabytes(usedHeapMemory)+" MB");
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}
}
