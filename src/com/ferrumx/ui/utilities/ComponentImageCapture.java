package com.ferrumx.ui.utilities;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import com.ferrumx.system.currentuser.User;
import com.ferrumx.ui.secondary.ExceptionUI;

public class ComponentImageCapture {
	
	private ComponentImageCapture() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static void getScreenshot(Component component, String name) {
		BufferedImage image = new BufferedImage(component.getBounds().width, component.getBounds().height, BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());
		
		try {
			File savedScreenshotFile = new File(User.getUsername()+"-"+name+".png");
			String screenshotFileName = savedScreenshotFile.getName();
			String screenshotFilePath = savedScreenshotFile.getAbsolutePath();
			
			ImageIO.write(image, "png", savedScreenshotFile);
			//fire a system tray notification for screenshot captured
			NotificationTray notifTray = new NotificationTray();
			TrayIcon trayIcon = notifTray.getTrayIcon();
			SystemTray sysTray = notifTray.getSysTray();
			//add contents to the display message and define action listeners for the notification
			String displayContent = "Screenshot "+screenshotFileName+" has been saved in\n"+screenshotFilePath+"\nClick to Open";
			notifTray.displayTray(displayContent, MessageType.INFO);
			trayIcon.setToolTip(displayContent);
			trayIcon.addActionListener(e->{
				try {
					Desktop.getDesktop().open(savedScreenshotFile);
				} catch (IOException e1) {
					new ExceptionUI("Screenshot Opening Error", e1.getMessage());
				} finally {
					sysTray.remove(trayIcon);
				}
			});
			//automatically remove the notification after 60s if the action event is not fired
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(60);
					sysTray.remove(trayIcon);
				} catch (InterruptedException e1) {
					new ExceptionUI("Notification Auto Removal Error", e1.getMessage());
					Thread.currentThread().interrupt();
				}
				
			}).start();
			
		} catch (IOException e) {
			new ExceptionUI("Screenshot Error", e.getMessage());
		}
	}
}
