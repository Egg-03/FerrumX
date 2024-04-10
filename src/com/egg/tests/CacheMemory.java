package com.egg.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.egg.system.hardware.Win32_AssociatedProcessorMemory;
import com.egg.system.hardware.Win32_CacheMemory;
import com.egg.system.hardware.Win32_Processor;

public class CacheMemory {

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		List<String> cpuID = Win32_Processor.getDeviceIDList();
		List<String> cacheID = null;
		
		Map<String, String> cache = null;
		
		for(String id:cpuID) {
			cacheID = Win32_AssociatedProcessorMemory.getCacheID(id);
			for(String currentCacheID:cacheID) {
				cache = Win32_CacheMemory.getCPUCache(currentCacheID);
				for(Map.Entry<String, String> currentCache: cache.entrySet())
					System.out.println(currentCache.getKey()+": "+currentCache.getValue());
				System.out.println();
			}
		}	
	}
}
