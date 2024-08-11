package com.ferrumx.ui.utilities;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
			ImageIO.write(image, "png", new File(User.getUsername()+"-"+name+".png"));
		} catch (IOException e) {
			new ExceptionUI("Screenshot Error", e.getMessage());
		}
	}
}
