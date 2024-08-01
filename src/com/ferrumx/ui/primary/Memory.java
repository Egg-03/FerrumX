package com.ferrumx.ui.primary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferrumx.system.hardware.Win32_PhysicalMemory;
import com.ferrumx.ui.secondary.ExceptionUI;

final class Memory {
	
	private Memory() {
		throw new IllegalStateException("Utility Class");
	}
	
	protected static boolean initializeMemory(JComboBox<String> memoryChoice, JTextField... memoryFields) {
		try {
			List<String> memorySlots = Win32_PhysicalMemory.getTag();
			for(String currentSlot: memorySlots)
				memoryChoice.addItem(currentSlot);
			
			Map<String, String> memoryProperties = Win32_PhysicalMemory.getMemory(memoryChoice.getItemAt(memoryChoice.getSelectedIndex()));
			memoryFields[0].setText(memoryProperties.get("Name"));
			memoryFields[1].setText(memoryProperties.get("Manufacturer"));
			memoryFields[2].setText(memoryProperties.get("Model"));
			memoryFields[3].setText(memoryProperties.get("OtherIdentifyingInfo"));
			memoryFields[4].setText(memoryProperties.get("PartNumber"));
			memoryFields[5].setText(memoryProperties.get("FormFactor"));
			memoryFields[6].setText(memoryProperties.get("BankLabel"));
			memoryFields[7].setText(memoryProperties.get("Capacity")+" Bytes");
			memoryFields[8].setText(memoryProperties.get("DataWidth")+ " Bit");
			memoryFields[9].setText(memoryProperties.get("Speed")+" MT/s");
			memoryFields[10].setText(memoryProperties.get("ConfiguredClockSpeed")+" MT/s");
			memoryFields[11].setText(memoryProperties.get("DeviceLocator"));
			memoryFields[12].setText(memoryProperties.get("SerialNumber"));
			
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Memory Error", e.getMessage()).setVisible(true);
			return false;
		}
		addMemoryChoiceActionListner(memoryChoice, memoryFields);
		return true;
	}
	
	private static void addMemoryChoiceActionListner(JComboBox<String> memoryChoice, JTextField... memoryFields) {
		memoryChoice.addActionListener(e->{
			try {
				Map<String, String> memoryProperties = Win32_PhysicalMemory.getMemory(memoryChoice.getItemAt(memoryChoice.getSelectedIndex()));
				memoryFields[0].setText(memoryProperties.get("Name"));
				memoryFields[1].setText(memoryProperties.get("Manufacturer"));
				memoryFields[2].setText(memoryProperties.get("Model"));
				memoryFields[3].setText(memoryProperties.get("OtherIdentifyingInfo"));
				memoryFields[4].setText(memoryProperties.get("PartNumber"));
				memoryFields[5].setText(memoryProperties.get("FormFactor"));
				memoryFields[6].setText(memoryProperties.get("BankLabel"));
				memoryFields[7].setText(memoryProperties.get("Capacity")+" Bytes");
				memoryFields[8].setText(memoryProperties.get("DataWidth")+ " Bit");
				memoryFields[9].setText(memoryProperties.get("Speed")+" MT/s");
				memoryFields[10].setText(memoryProperties.get("ConfiguredClockSpeed")+" MT/s");
				memoryFields[11].setText(memoryProperties.get("DeviceLocator"));
				memoryFields[12].setText(memoryProperties.get("SerialNumber"));
				
			} catch (IndexOutOfBoundsException | IOException e2) {
				new ExceptionUI("Memory Error", e2.getMessage()).setVisible(true);
			}
		});
	}
}
