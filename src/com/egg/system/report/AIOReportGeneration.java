package com.egg.system.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.egg.system.currentuser.User;
import com.egg.system.hardware.Win32_AssociatedProcessorMemory;
import com.egg.system.hardware.Win32_BIOS;
import com.egg.system.hardware.Win32_Baseboard;
import com.egg.system.hardware.Win32_CacheMemory;
import com.egg.system.hardware.Win32_DiskDrive;
import com.egg.system.hardware.Win32_NetworkAdapter;
import com.egg.system.hardware.Win32_PhysicalMemory;
import com.egg.system.hardware.Win32_PortConnector;
import com.egg.system.hardware.Win32_Printer;
import com.egg.system.hardware.Win32_Processor;
import com.egg.system.hardware.Win32_SoundDevice;
import com.egg.system.hardware.Win32_VideoController;
import com.egg.system.networking.Win32_NetworkAdapterConfiguration;
import com.egg.system.networking.Win32_NetworkAdapterSetting;
import com.egg.system.operating_system.Win32_DiskDriveToDiskPartition;
import com.egg.system.operating_system.Win32_LogicalDiskToPartition;
import com.egg.system.operating_system.Win32_OperatingSystem;
import com.egg.system.operating_system.Win32_SystemDriver;
import com.egg.system.operating_system.Win32_TimeZone;

public class AIOReportGeneration {
	protected static void generate(JProgressBar progress, JLabel label, JButton button) {
		new Thread(()-> {
		try(FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"\\Desktop\\Report.txt");){
			PrintStream report = new PrintStream(fos);
			
			button.setEnabled(false);
			button.setText("Generating");
			
			reportOS(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering OS Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(6));
			reportUser(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering User Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(13));
			reportTimeZone(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering TimeZone Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(20));
			reportCPU(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering CPU Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(28));
			reportCPUCache(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering CPU Cache Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(35));
			reportRAM(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering RAM Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(42));
			reportGPU(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Video Controller Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(50));
			reportMotherboard(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Mainboard Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(57));
			reportBIOS(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering BIOS Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(64));
			reportIO(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering I/O Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(70));
			reportNetwork(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Network Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(77));
			reportDisk(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Storage Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(85));
			reportPrinter(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Printer Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(90));
			reportSound(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Audio Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(95));
			reportDrivers(report);
			SwingUtilities.invokeLater(() -> label.setText("Gathering Driver Info"));
			SwingUtilities.invokeLater(() -> progress.setValue(100));
			report.close();
			
			button.setEnabled(true);
			label.setText("Finished");
			button.setText("Generate");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}).start();
	}

	private static void reportDrivers(PrintStream report) throws IOException {
		List<String> drivers = Win32_SystemDriver.getDrivers();
		Iterator<String> itr = drivers.iterator();
		
		report.println("----------------------SYSTEM DRIVERS------------------------");
		while(itr.hasNext())
			report.println(itr.next());	
	}

	private static void reportSound(PrintStream report) throws IOException {
		List<String> deviceIDs = Win32_SoundDevice.getSoundDeviceID();
		Map<String, String> currentAudio;
		
		report.println("----------------------AUDIO INFO------------------------");
		for(String currentID : deviceIDs) {
			currentAudio = Win32_SoundDevice.getCurrentAudioDevice(currentID);
			for(Map.Entry<String, String> entry: currentAudio.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
	    }
	}

	private static void reportPrinter(PrintStream report) throws IOException {
		List<String> deviceIDs = Win32_Printer.getDeviceIDList();
		Map<String, String> currentPrinter;
		
		report.println("----------------------PRINTER INFO------------------------");
		for(String currentID : deviceIDs) {
			currentPrinter = Win32_Printer.getCurrentPrinter(currentID);
			for(Map.Entry<String, String> entry: currentPrinter.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
	    }
	}

	private static void reportDisk(PrintStream report) throws IOException {
		List<String> diskID = Win32_DiskDrive.getDriveID();
		List<String> diskPartition;
		Map<String, String> disk = null;
		
		report.println("----------------------STORAGE INFO------------------------");
		for(String id:diskID) {
			disk = Win32_DiskDrive.getDrive(id);
			diskPartition = Win32_DiskDriveToDiskPartition.getPartitionList(id);
			for(Map.Entry<String, String> entry: disk.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			
			for(String currentPartition: diskPartition) {
				report.println("Partition: "+currentPartition+", Drive Letter: "+Win32_LogicalDiskToPartition.getDriveLetter(currentPartition));
			}
			report.println();
		}
	}

	private static void reportNetwork(PrintStream report) throws IOException {
		List<String> deviceIDs = Win32_NetworkAdapter.getDeviceIDList();
		Map<String, String> networkAdapter;
		Map<String, String> networkAdapterConfiguration;
		String index = "";
		
		report.println("----------------------NETWORK INFO------------------------");
		for(String currentID : deviceIDs) {
			networkAdapter = Win32_NetworkAdapter.getNetworkAdapters(currentID);
			index = Win32_NetworkAdapterSetting.getIndex(currentID);
			networkAdapterConfiguration = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(index);
			for(Map.Entry<String, String> entry: networkAdapter.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
			for(Map.Entry<String, String> entry: networkAdapterConfiguration.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
		}
	}

	private static void reportIO(PrintStream report) throws IOException {
		List<String> portID = Win32_PortConnector.getBaseboardPortID();
		Map<String, String> ports = null;
		
		report.println("----------------------MAINBOARD I/O INFO------------------------");
		for(String id:portID) {
			ports = Win32_PortConnector.getBaseboardPorts(id);
			for(Map.Entry<String, String> port: ports.entrySet())
				report.println(port.getKey()+": "+port.getValue());
			report.println();
		}	
	}

	private static void reportBIOS(PrintStream report) throws IOException {
		report.println("----------------------BIOS INFO------------------------");
		Map<String, String> BIOS = Win32_BIOS.getPrimaryBIOS();
		for(Map.Entry<String, String> entry: BIOS.entrySet())
			report.println(entry.getKey()+": "+entry.getValue());
	}

	private static void reportMotherboard(PrintStream report) throws IOException {
		report.println("----------------------MAINBOARD------------------------");
		Map<String, String> motherboard = Win32_Baseboard.getMotherboard();
		for(Map.Entry<String, String> entry: motherboard.entrySet())
			report.println(entry.getKey()+": "+entry.getValue());
	}

	private static void reportGPU(PrintStream report) throws IOException {
		List<String> gpuIDs = Win32_VideoController.getGPUID();
		Map<String, String> currentGPU;
		
		report.println("----------------------VIDEO CONTROLLER------------------------");
		for(String currentID : gpuIDs) {
			currentGPU = Win32_VideoController.getGPU(currentID);
			for(Map.Entry<String, String> entry: currentGPU.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
		}
	}

	private static void reportRAM(PrintStream report) throws IOException {
		List<String> memoryID = Win32_PhysicalMemory.getTagOrBank();
		Map<String, String> memory = null;
		
		report.println("----------------------SPD------------------------");
		for(String id:memoryID) {
			memory = Win32_PhysicalMemory.getMemory(id);
			for(Map.Entry<String, String> entry: memory.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
		}
	}

	private static void reportCPUCache(PrintStream report) throws IOException {
		List<String> cpuID = Win32_Processor.getDeviceIDList();
		List<String> cacheID = null;
		
		Map<String, String> cache = null;
		report.println("----------------------CPU CACHE------------------------");
		for(String id:cpuID) {
			cacheID = Win32_AssociatedProcessorMemory.getCacheID(id);
			for(String currentCacheID:cacheID) {
				cache = Win32_CacheMemory.getCPUCache(currentCacheID);
				for(Map.Entry<String, String> currentCache: cache.entrySet())
					report.println(currentCache.getKey()+": "+currentCache.getValue());
				report.println();
			}
		}
	}

	private static void reportOS(PrintStream report) throws IOException {
		List<String> oslist = Win32_OperatingSystem.getOSList();
		Map<String, String> osinfo;
		
		for(String currentOS : oslist) {
			osinfo = Win32_OperatingSystem.getOSInfo(currentOS);
			
			report.println("----------------------OS INFO------------------------");
			for(Map.Entry<String, String> entry: osinfo.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
		}
	}
	
	private static void reportCPU(PrintStream report) throws IOException {
		List<String> deviceIDs = Win32_Processor.getDeviceIDList();
		Map<String, String> currentCPU;
		
		report.println("----------------------CPU INFO------------------------");
		for(String currentID : deviceIDs) {
			currentCPU = Win32_Processor.getCurrentProcessor(currentID);
			for(Map.Entry<String, String> entry: currentCPU.entrySet())
				report.println(entry.getKey()+": "+entry.getValue());
			report.println();
		}
	}
	
	private static void reportTimeZone(PrintStream report) throws IOException {
		Map<String, String> currentTimeZone = Win32_TimeZone.getOSTimeZone();
		
		report.println("----------------------TIMEZONE------------------------");
		for(Map.Entry<String, String> entry: currentTimeZone.entrySet())
			report.println(entry.getKey()+": "+entry.getValue());
		report.println();
    }

	private static void reportUser(PrintStream report){
		report.println("----------------------USER INFO------------------------");
		report.println("Current Username: "+User.getUsername());
		report.println("User Home Directory: "+User.getHome());
	}
}
