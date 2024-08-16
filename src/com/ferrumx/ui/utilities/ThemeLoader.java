package com.ferrumx.ui.utilities;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButtonMenuItem;

import org.ini4j.Wini;

import com.ferrumx.ui.secondary.ExceptionUI;

public class ThemeLoader {
	private ThemeLoader() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static void store(String themeClass) {
		
		try {
			//if the settings folder or the theme.ini file does not exist, create one
			if(!new File("settings").isDirectory()) {
				new File("settings").mkdir();
				if(new File("settings/theme.ini").createNewFile()) {
					new NotificationTray().displayTrayAutoRemove("Created the theme.ini file. All selected themes will be stored and loaded from this file", MessageType.INFO);
				}
				
			}
			else if(!new File("settings/theme.ini").isFile() && new File("settings/theme.ini").createNewFile()) {
				new NotificationTray().displayTrayAutoRemove("Created the theme.ini file. All selected themes will be stored and loaded from this file", MessageType.INFO);
			}
			
			//if the theme file exists, store the currently selected theme class
			Wini ini = new Wini(new File("settings/theme.ini"));
			ini.put("Theme", "CurrentTheme", themeClass);
			ini.store();
		} catch (IOException e) {
			new ExceptionUI("Theme Save Error", e.getMessage()).setVisible(true);
		}
	}
	
	//load the stored theme from the ini file
	public static String load() {
		String defaultTheme = "com.formdev.flatlaf.themes.FlatMacDarkLaf";
		try {
			//if the settings folder or the theme.ini file does not exist, create one
			//and store it with the default black theme
			if(!new File("settings").isDirectory()) {
				new File("settings").mkdir();
				if(new File("settings/theme.ini").createNewFile()) {
					//if a new theme.ini file was created, load with the default black theme
					Wini ini = new Wini(new File("settings/theme.ini"));
					ini.put("Theme", "CurrentTheme", defaultTheme);
					ini.store();
				}
				return defaultTheme;
			}
			else if(!new File("settings/theme.ini").isFile()) {
				if(new File("settings/theme.ini").createNewFile()) {
					//if a new theme.ini file was created, load with the default black theme
					Wini ini = new Wini(new File("settings/theme.ini"));
					ini.put("Theme", "CurrentTheme", defaultTheme);
					ini.store();
				}
				return defaultTheme;
			}
			 
			//load theme from the saved ini file
			Wini ini = new Wini(new File("settings/theme.ini"));
			return ini.get("Theme", "CurrentTheme");
				
		} catch (IOException e) {
			e.printStackTrace();
			return defaultTheme;
		}
	}
	
	//will read the current loaded theme class name and select the appropriate theme button
	public static void notifyCurrentThemeUsage(JRadioButtonMenuItem...themeButtons) {
		switch(ThemeLoader.load()) {
			case "com.formdev.flatlaf.themes.FlatMacDarkLaf":
				themeButtons[0].setSelected(true);
				break;
			case "com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme":
				themeButtons[1].setSelected(true);
				break;
			case "com.formdev.flatlaf.FlatDarculaLaf":
				themeButtons[2].setSelected(true);
				break;
			case "com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme":
				themeButtons[3].setSelected(true);
				break;
			case "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme":
				themeButtons[4].setSelected(true);
				break;
			case "com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme":
				themeButtons[5].setSelected(true);
				break;
			default:
				themeButtons[0].setSelected(true);
		}				
	}
}