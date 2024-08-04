package com.ferrumx.ui.primary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferrumx.system.operating_system.Win32_OperatingSystem;
import com.ferrumx.ui.secondary.ExceptionUI;

final class OperatingSystem {
	
	private OperatingSystem() {
		throw new IllegalStateException("Utility Class");
	}
	
	protected static boolean getOsProperties(JComboBox<String> osChoice, JTextField...osFields) {
		try {
			List<String> osList = Win32_OperatingSystem.getOSList();
			for(String os: osList)
				osChoice.addItem(os);
			
			Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osChoice.getItemAt(osChoice.getSelectedIndex()));
			osFields[0].setText(osProperties.get("Caption"));
			osFields[1].setText(osProperties.get("Version"));
			osFields[2].setText(osProperties.get("Manufacturer"));
			osFields[3].setText(osProperties.get("OSArchitecture"));
			osFields[4].setText(osProperties.get("BuildNumber"));
			osFields[5].setText(osProperties.get("InstallDate"));
			osFields[6].setText(osProperties.get("LastBootUpTime"));
			osFields[7].setText(osProperties.get("SerialNumber"));
			osFields[8].setText(osProperties.get("Primary"));
			osFields[9].setText(osProperties.get("Distributed"));
			osFields[10].setText(osProperties.get("PortableOperatingSystem"));
			osFields[11].setText(osProperties.get("CSName"));
			osFields[12].setText(osProperties.get("NumberOfUsers"));
			osFields[13].setText(osProperties.get("RegisteredUser"));
			osFields[14].setText(osProperties.get("MUILanguages"));
			osFields[15].setText(osProperties.get("SystemDrive"));
			osFields[16].setText(osProperties.get("WindowsDirectory"));
			osFields[17].setText(osProperties.get("SystemDirectory"));
			
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("OS Error", e.getMessage()).setVisible(true);
			return false;
		}
		addOsChoiceActionListener(osChoice, osFields);
		return true;
	}

	private static void addOsChoiceActionListener(JComboBox<String> osChoice, JTextField[] osFields) {
		osChoice.addActionListener(e->{
			try {
				Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osChoice.getItemAt(osChoice.getSelectedIndex()));
				osFields[0].setText(osProperties.get("Caption"));
				osFields[1].setText(osProperties.get("Version"));
				osFields[2].setText(osProperties.get("Manufacturer"));
				osFields[3].setText(osProperties.get("OSArchitecture"));
				osFields[4].setText(osProperties.get("BuildNumber"));
				osFields[5].setText(osProperties.get("InstallDate"));
				osFields[6].setText(osProperties.get("LastBootUpTime"));
				osFields[7].setText(osProperties.get("SerialNumber"));
				osFields[8].setText(osProperties.get("Primary"));
				osFields[9].setText(osProperties.get("Distributed"));
				osFields[10].setText(osProperties.get("PortableOperatingSystem"));
				osFields[11].setText(osProperties.get("CSName"));
				osFields[12].setText(osProperties.get("NumberOfUsers"));
				osFields[13].setText(osProperties.get("RegisteredUser"));
				osFields[14].setText(osProperties.get("MUILanguages"));
				osFields[15].setText(osProperties.get("SystemDrive"));
				osFields[16].setText(osProperties.get("WindowsDirectory"));
				osFields[17].setText(osProperties.get("SystemDirectory"));
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("OS Error", e1.getMessage()).setVisible(true);
			}
			
		});
	}
}
