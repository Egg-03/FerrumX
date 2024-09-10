package com.ferrumx.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.exceptions.ShellException;
import com.ferrumx.system.hardware.Win32_SoundDevice;

public class SoundDeviceTest {

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException, ShellException, InterruptedException {
		List<String> deviceIDs = Win32_SoundDevice.getSoundDeviceID();
		Map<String, String> currentAudio;

		System.out.println(deviceIDs);

		for (String currentID : deviceIDs) {
			currentAudio = Win32_SoundDevice.getCurrentAudioDevice(currentID);
			for (Map.Entry<String, String> entry : currentAudio.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}

}
