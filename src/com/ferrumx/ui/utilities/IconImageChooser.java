package com.ferrumx.ui.utilities;

import javax.swing.JLabel;

import com.ferrumx.ui.primary.FerrumX;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class IconImageChooser {
	
	private IconImageChooser() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static void cpuImageChooser(JLabel icon, String cpuManufacturer) {
		if (cpuManufacturer.equals("AuthenticAMD")) {
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/cpu_manufactuer_icons/amd.svg")));
		} else if (cpuManufacturer.equals("GenuineIntel")) {
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/cpu_manufactuer_icons/intel.svg")));
		}
	}
	
	public static void gpuImageChooser(JLabel icon, String gpuManufacturer) {
		if(gpuManufacturer.contains("AMD"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/gpu_manufacturer_icons/amd.svg")));
		else if(gpuManufacturer.contains("NVIDIA"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/gpu_manufacturer_icons/nvidia.svg")));
		else if(gpuManufacturer.contains("Intel"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/gpu_manufacturer_icons/intel.svg")));
	}
	
	public static void osImageChooser(JLabel icon, String osVersionName) {
		if(osVersionName.contains("Windows 8") || osVersionName.contains("Windows 8.1"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/windows_logos/windows8.svg")));
		else if(osVersionName.contains("Windows 10"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/windows_logos/windows10.svg")));
		else if(osVersionName.contains("Windows 11"))
			icon.setIcon(new FlatSVGIcon(FerrumX.class.getResource("/resources/windows_logos/windows11.svg")));
	}
	
}
