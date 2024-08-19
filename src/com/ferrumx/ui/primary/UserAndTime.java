package com.ferrumx.ui.primary;

import java.io.IOException;
import java.util.Map;

import javax.swing.JTextField;

import com.ferrumx.system.currentuser.User;
import com.ferrumx.system.operating_system.Win32_TimeZone;
import com.ferrumx.ui.secondary.ExceptionUI;

final class UserAndTime {
	private UserAndTime() {
		throw new IllegalStateException("Utility Class");
	}

	protected static boolean initializeUserAndTime(JTextField... userTimeFields) {
		userTimeFields[0].setText(User.getUsername());
		userTimeFields[1].setText(User.getHome());

		try {
			Map<String, String> timeZone = Win32_TimeZone.getOSTimeZone();
			if (timeZone.isEmpty()) {
				new ExceptionUI("Timezone Initialization Error", "FATAL ERROR: No valid Time zone found")
						.setVisible(true);
				return false;
			}
			userTimeFields[2].setText(timeZone.get("StandardName"));
			userTimeFields[3].setText(timeZone.get("Caption"));
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Time Zone Error", e.getMessage()).setVisible(true);
			return false;
		}
		return true;
	}
}
