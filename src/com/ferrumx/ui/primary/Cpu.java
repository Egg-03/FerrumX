package com.ferrumx.ui.primary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferrumx.system.hardware.Win32_AssociatedProcessorMemory;
import com.ferrumx.system.hardware.Win32_CacheMemory;
import com.ferrumx.system.hardware.Win32_Processor;
import com.ferrumx.ui.secondary.ExceptionUI;

final class Cpu {
	
	private Cpu() {
		throw new IllegalStateException("Utility Class");
	}
	
	protected static boolean initializeCpu(JComboBox<String> cpuChoice, JTextField... cpuFields) {
		try {
			List<String> cpuList = Win32_Processor.getProcessorList();
			for(String cpu: cpuList) {
				cpuChoice.addItem(cpu);
			}
			
			String currentCpu = cpuChoice.getItemAt(cpuChoice.getSelectedIndex());
			Map<String, String> cpuProperties = Win32_Processor.getCurrentProcessor(currentCpu);
			cpuFields[0].setText(cpuProperties.get("Name"));
			cpuFields[1].setText(cpuProperties.get("NumberOfCores"));
			cpuFields[2].setText(cpuProperties.get("ThreadCount"));
			cpuFields[3].setText(cpuProperties.get("NumberOfLogicalProcessors"));
			cpuFields[4].setText(cpuProperties.get("Manufacturer"));
			cpuFields[5].setText(cpuProperties.get("AddressWidth")+" bit");
			cpuFields[6].setText(cpuProperties.get("SocketDesignation"));
			cpuFields[7].setText(cpuProperties.get("ExtClock")+ "MHz");
			cpuFields[9].setText(cpuProperties.get("MaxClockSpeed")+ "MHz");
			cpuFields[10].setText(cpuProperties.get("Version"));
			cpuFields[11].setText(cpuProperties.get("Caption"));
			cpuFields[12].setText(cpuProperties.get("Family"));
			cpuFields[13].setText(cpuProperties.get("Stepping"));
			cpuFields[14].setText(cpuProperties.get("VirtualizationFirmwareEnabled"));
			cpuFields[15].setText(cpuProperties.get("ProcessorID"));
			
			cpuFields[8].setText(String.valueOf((Float.valueOf(cpuProperties.get("MaxClockSpeed"))/Float.valueOf(cpuProperties.get("ExtClock")))));
			List<String> cpuCacheList = Win32_AssociatedProcessorMemory.getCacheID(currentCpu);
			for(String currentCacheId: cpuCacheList) {
				Map<String, String> cpuCacheProperties = Win32_CacheMemory.getCPUCache(currentCacheId);
				if(cpuCacheProperties.get("Purpose").equals("L1 - Cache")) {
					cpuFields[16].setText(cpuCacheProperties.get("InstalledSize")+" KB");
					cpuFields[17].setText(cpuCacheProperties.get("Associativity"));
				}
				
				if(cpuCacheProperties.get("Purpose").equals("L2 - Cache")) {
					cpuFields[18].setText(cpuCacheProperties.get("InstalledSize")+" KB");
					cpuFields[19].setText(cpuCacheProperties.get("Associativity"));
				}
				
				if(cpuCacheProperties.get("Purpose").equals("L3 - Cache")) {
					cpuFields[20].setText(cpuCacheProperties.get("InstalledSize")+" KB");
					cpuFields[21].setText(cpuCacheProperties.get("Associativity"));
				}
			}
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("CPU Error", e.getMessage()).setVisible(true);
			return false;
		} catch (NumberFormatException e2) {
			cpuFields[8].setText("N/A");
			return true;
		}
		
		addCpuChoiceActionEvent(cpuChoice, cpuFields);
		return true;
	}
	
	private static void addCpuChoiceActionEvent(JComboBox<String> cpuChoice, JTextField...cpuFields) {
		cpuChoice.addActionListener(e->{
			try {
					String currentCpu = cpuChoice.getItemAt(cpuChoice.getSelectedIndex());
					Map<String, String> cpuProperties = Win32_Processor.getCurrentProcessor(currentCpu);
					cpuFields[0].setText(cpuProperties.get("Name"));
					cpuFields[1].setText(cpuProperties.get("NumberOfCores"));
					cpuFields[2].setText(cpuProperties.get("ThreadCount"));
					cpuFields[3].setText(cpuProperties.get("NumberOfLogicalProcessors"));
					cpuFields[4].setText(cpuProperties.get("Manufacturer"));
					cpuFields[5].setText(cpuProperties.get("AddressWidth")+" bit");
					cpuFields[6].setText(cpuProperties.get("SocketDesignation"));
					cpuFields[7].setText(cpuProperties.get("ExtClock")+ "MHz");
					cpuFields[9].setText(cpuProperties.get("MaxClockSpeed")+ "MHz");
					cpuFields[10].setText(cpuProperties.get("Version"));
					cpuFields[11].setText(cpuProperties.get("Caption"));
					cpuFields[12].setText(cpuProperties.get("Family"));
					cpuFields[13].setText(cpuProperties.get("Stepping"));
					cpuFields[14].setText(cpuProperties.get("VirtualizationFirmwareEnabled"));
					cpuFields[15].setText(cpuProperties.get("ProcessorID"));
				
					cpuFields[8].setText(String.valueOf((Float.valueOf(cpuProperties.get("MaxClockSpeed"))/Float.valueOf(cpuProperties.get("ExtClock")))));
					List<String> cpuCacheList = Win32_AssociatedProcessorMemory.getCacheID(currentCpu);
					for(String currentCacheId: cpuCacheList) {
						Map<String, String> cpuCacheProperties = Win32_CacheMemory.getCPUCache(currentCacheId);
						if(cpuCacheProperties.get("Purpose").equals("L1 - Cache")) {
							cpuFields[16].setText(cpuCacheProperties.get("InstalledSize")+" KB");
							cpuFields[17].setText(cpuCacheProperties.get("Associativity"));
						}
					
						if(cpuCacheProperties.get("Purpose").equals("L2 - Cache")) {
							cpuFields[18].setText(cpuCacheProperties.get("InstalledSize")+" KB");
							cpuFields[19].setText(cpuCacheProperties.get("Associativity"));
						}
					
						if(cpuCacheProperties.get("Purpose").equals("L3 - Cache")) {
							cpuFields[20].setText(cpuCacheProperties.get("InstalledSize")+" KB");
							cpuFields[21].setText(cpuCacheProperties.get("Associativity"));
						}
					}
				} catch (IndexOutOfBoundsException | IOException e2) {
				new ExceptionUI("CPU Error", e2.getMessage()).setVisible(true);
				} catch (NumberFormatException e3) {
				cpuFields[8].setText("N/A");
				}
			});
	}
}
