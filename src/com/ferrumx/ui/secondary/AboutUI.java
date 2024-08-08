package com.ferrumx.ui.secondary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Cursor;

public class AboutUI extends JFrame {
	private static final long serialVersionUID = 4517185644185035860L;
	private static String appLink = "https://github.com/Egg-03/FerrumX";
	private static String devLink = "https://github.com/Egg-03";
	private static String iconLink = "https://github.com/Soumil-Biswas";
	private static String themeLink = "https://github.com/JFormDesigner/FlatLaf";
	
	public AboutUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutUI.class.getResource("/resources/icon_main.png")));
		setBounds(100,100,540,230);
		setTitle("About FerrumX");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel wrapperVersionPanel = new JPanel();
		wrapperVersionPanel.setBorder(new TitledBorder(null, "Wrapper Version", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_wrapperVersionPanel = new GridBagConstraints();
		gbc_wrapperVersionPanel.fill = GridBagConstraints.BOTH;
		gbc_wrapperVersionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_wrapperVersionPanel.gridx = 0;
		gbc_wrapperVersionPanel.gridy = 0;
		mainPanel.add(wrapperVersionPanel, gbc_wrapperVersionPanel);
		wrapperVersionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel wrapperVersion = new JLabel("v1.2.4");
		wrapperVersion.setHorizontalAlignment(SwingConstants.CENTER);
		wrapperVersionPanel.add(wrapperVersion);
		wrapperVersion.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(new TitledBorder(null, "Click to open the github page", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_logoPanel = new GridBagConstraints();
		gbc_logoPanel.fill = GridBagConstraints.BOTH;
		gbc_logoPanel.insets = new Insets(0, 0, 5, 5);
		gbc_logoPanel.gridx = 1;
		gbc_logoPanel.gridy = 0;
		mainPanel.add(logoPanel, gbc_logoPanel);
		GridBagLayout gbl_logoPanel = new GridBagLayout();
		gbl_logoPanel.columnWidths = new int[]{0, 0};
		gbl_logoPanel.rowHeights = new int[]{0, 0};
		gbl_logoPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_logoPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		logoPanel.setLayout(gbl_logoPanel);
		
		JLabel logo = new JLabel("");
		logo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI(appLink));
                } catch (URISyntaxException | IOException ex) {
                    new ExceptionUI("Link Visit Error", ex.getMessage()).setVisible(true);
                }
			}
		});
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		logoPanel.add(logo, gbc_logo);
		logo.setIcon(new ImageIcon(AboutUI.class.getResource("/resources/logo_main_broad.png")));
		
		JPanel guiVersionPanel = new JPanel();
		guiVersionPanel.setBorder(new TitledBorder(null, "GUI Version", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_guiVersionPanel = new GridBagConstraints();
		gbc_guiVersionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_guiVersionPanel.fill = GridBagConstraints.BOTH;
		gbc_guiVersionPanel.gridx = 2;
		gbc_guiVersionPanel.gridy = 0;
		mainPanel.add(guiVersionPanel, gbc_guiVersionPanel);
		guiVersionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel guiVersion = new JLabel("v08082024 Beta");
		guiVersion.setHorizontalAlignment(SwingConstants.CENTER);
		guiVersionPanel.add(guiVersion);
		guiVersion.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		
		JPanel contributionPanel = new JPanel();
		contributionPanel.setBorder(new TitledBorder(null, "Contribution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_contributionPanel = new GridBagConstraints();
		gbc_contributionPanel.gridwidth = 3;
		gbc_contributionPanel.insets = new Insets(0, 0, 0, 5);
		gbc_contributionPanel.fill = GridBagConstraints.BOTH;
		gbc_contributionPanel.gridx = 0;
		gbc_contributionPanel.gridy = 1;
		mainPanel.add(contributionPanel, gbc_contributionPanel);
		GridBagLayout gbl_contributionPanel = new GridBagLayout();
		gbl_contributionPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contributionPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contributionPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contributionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contributionPanel.setLayout(gbl_contributionPanel);
		
		JLabel developer = new JLabel("Developed By");
		GridBagConstraints gbc_developer = new GridBagConstraints();
		gbc_developer.insets = new Insets(0, 0, 5, 5);
		gbc_developer.anchor = GridBagConstraints.EAST;
		gbc_developer.gridx = 0;
		gbc_developer.gridy = 0;
		contributionPanel.add(developer, gbc_developer);
		
		JTextField developerLink = new JTextField();
		developerLink.setForeground(new Color(0, 191, 255));
		developerLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		developerLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI(devLink));
                } catch (URISyntaxException | IOException ex) {
                	new ExceptionUI("Link Visit Error", ex.getMessage()).setVisible(true);
                }
			}
		});
		developerLink.setText(devLink);
		developerLink.setEditable(false);
		GridBagConstraints gbc_developerLink = new GridBagConstraints();
		gbc_developerLink.insets = new Insets(0, 0, 5, 0);
		gbc_developerLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_developerLink.gridx = 1;
		gbc_developerLink.gridy = 0;
		contributionPanel.add(developerLink, gbc_developerLink);
		developerLink.setColumns(10);
		
		JLabel iconMaker = new JLabel("Logo and Icons By");
		GridBagConstraints gbc_iconMaker = new GridBagConstraints();
		gbc_iconMaker.anchor = GridBagConstraints.EAST;
		gbc_iconMaker.insets = new Insets(0, 0, 5, 5);
		gbc_iconMaker.gridx = 0;
		gbc_iconMaker.gridy = 1;
		contributionPanel.add(iconMaker, gbc_iconMaker);
		
		JTextField iconMakerLink = new JTextField();
		iconMakerLink.setForeground(new Color(0, 191, 255));
		iconMakerLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconMakerLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI(iconLink));
                } catch (URISyntaxException | IOException ex) {
                	new ExceptionUI("Link Visit Error", ex.getMessage()).setVisible(true);
                }
			}
		});
		iconMakerLink.setText(iconLink);
		iconMakerLink.setEditable(false);
		GridBagConstraints gbc_iconMakerLink = new GridBagConstraints();
		gbc_iconMakerLink.insets = new Insets(0, 0, 5, 0);
		gbc_iconMakerLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconMakerLink.gridx = 1;
		gbc_iconMakerLink.gridy = 1;
		contributionPanel.add(iconMakerLink, gbc_iconMakerLink);
		iconMakerLink.setColumns(10);
		
		JLabel themeSupport = new JLabel("Theme Support");
		GridBagConstraints gbc_themeSupport = new GridBagConstraints();
		gbc_themeSupport.anchor = GridBagConstraints.EAST;
		gbc_themeSupport.insets = new Insets(0, 0, 0, 5);
		gbc_themeSupport.gridx = 0;
		gbc_themeSupport.gridy = 2;
		contributionPanel.add(themeSupport, gbc_themeSupport);
		
		JTextField themeSupportLink = new JTextField();
		themeSupportLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI(themeLink));
                } catch (URISyntaxException | IOException ex) {
                	new ExceptionUI("Link Visit Error", ex.getMessage()).setVisible(true);
                }
			}
		});
		themeSupportLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		themeSupportLink.setForeground(new Color(0, 191, 255));
		themeSupportLink.setText(themeLink);
		themeSupportLink.setEditable(false);
		GridBagConstraints gbc_themeSupportLink = new GridBagConstraints();
		gbc_themeSupportLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_themeSupportLink.gridx = 1;
		gbc_themeSupportLink.gridy = 2;
		contributionPanel.add(themeSupportLink, gbc_themeSupportLink);
		themeSupportLink.setColumns(10);
	}
}
