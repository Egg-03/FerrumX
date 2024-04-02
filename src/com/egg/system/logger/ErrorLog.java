package com.egg.system.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
	private static boolean use_flag = false;
	private static FileOutputStream fos;
	
	synchronized public void log(String message) {
		while(ErrorLog.use_flag)
			try {
				wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		
		use_flag = true;
		try {
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			fos = new FileOutputStream(System.getProperty("user.home")+"\\Desktop\\ERRORLOG-"+ldt.format(dtf2)+".log", true);
			
			String timestamp = ldt.format(dtf);
			
			fos.write(timestamp.getBytes());
			fos.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			use_flag= false;
		}
	}
}