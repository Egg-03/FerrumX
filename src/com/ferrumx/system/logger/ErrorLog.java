package com.ferrumx.system.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used by {@link com.ferrumx.formatter.cim.CIM_ML} and
 * {@link com.ferrumx.formatter.cim.CIM_SL} classes to log errors in a file.
 * While not necessary, the developers can use this class to log additional
 * errors if the development demands it.
 *
 * @author Egg-03
 * @version 1.1.0
 */
public class ErrorLog {
	private static boolean use_flag = false;

	/**
	 * This method logs the given message in a file. The naming convention of the
	 * file is as follows: "FerrumX_ERRORLOG-currentDate.log" This means new log
	 * files are created on a day basis and a single log file will contain all the
	 * error logs for a particular day All files are created in the current working
	 * directory
	 *
	 * @param message takes the error message and logs it to a file
	 */
	public synchronized void log(String message) {
		while (ErrorLog.use_flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		use_flag = true;

		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		try (FileOutputStream fos = new FileOutputStream("FerrumX_ERRORLOG-" + ldt.format(dtf2) + ".log", true);) {

			String timestamp = ldt.format(dtf);

			fos.write(timestamp.getBytes());
			fos.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			use_flag = false;
			notifyAll();
		}
	}
}
