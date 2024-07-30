package com.ferrumx.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class FerrumX {

	private JFrame mainFrame;
	private JTextField hwidTf;
	private JComboBox<String> cpuChoice;
	private JTextField cpuCoreTf;
	private JTextField cpuThreadTf;
	private JTextField cpuLogicProcessorTf;
	private JTextField cpuNameTf;
	private JTextField addressWidthTf;
	private JTextField cpuSocketTf;
	private JTextField cpuManufacturerTf;
	private JTextField cpuBaseClockTf;
	private JTextField multiplierTf;
	private JTextField effectiveClockTf;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FerrumX window = new FerrumX();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FerrumX() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("FerrumX");
		mainFrame.setResizable(false);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(FerrumX.class.getResource("/resources/icon_main.png")));
		mainFrame.setBounds(100, 100, 600, 450);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainFrame.getContentPane().add(tabbedPane);
		
		JPanel hwidCpuPanel = new JPanel();
		tabbedPane.addTab("CPU", null, hwidCpuPanel, null);
		tabbedPane.setEnabledAt(0, true);
		GridBagLayout gbl_hwidCpuPanel = new GridBagLayout();
		gbl_hwidCpuPanel.columnWidths = new int[]{0, 0};
		gbl_hwidCpuPanel.rowHeights = new int[]{0, 0, 0};
		gbl_hwidCpuPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_hwidCpuPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		hwidCpuPanel.setLayout(gbl_hwidCpuPanel);
		
		JPanel hwidPanel = new JPanel();
		hwidPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "HardwareID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(221, 221, 221)));
		GridBagConstraints gbchwidPanel = new GridBagConstraints();
		gbchwidPanel.insets = new Insets(0, 0, 5, 0);
		gbchwidPanel.fill = GridBagConstraints.BOTH;
		gbchwidPanel.gridx = 0;
		gbchwidPanel.gridy = 0;
		hwidCpuPanel.add(hwidPanel, gbchwidPanel);
		GridBagLayout gbl_hwidPanel = new GridBagLayout();
		gbl_hwidPanel.columnWidths = new int[]{0, 0, 0};
		gbl_hwidPanel.rowHeights = new int[]{0, 0};
		gbl_hwidPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_hwidPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		hwidPanel.setLayout(gbl_hwidPanel);
		
		JLabel hwidlabel = new JLabel("HWID");
		GridBagConstraints gbc_hwidlabel = new GridBagConstraints();
		gbc_hwidlabel.insets = new Insets(0, 0, 0, 5);
		gbc_hwidlabel.anchor = GridBagConstraints.EAST;
		gbc_hwidlabel.gridx = 0;
		gbc_hwidlabel.gridy = 0;
		hwidPanel.add(hwidlabel, gbc_hwidlabel);
		
		hwidTf = new JTextField();
		hwidTf.setEditable(false);
		GridBagConstraints gbc_hwidTf = new GridBagConstraints();
		gbc_hwidTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_hwidTf.gridx = 1;
		gbc_hwidTf.gridy = 0;
		hwidPanel.add(hwidTf, gbc_hwidTf);
		hwidTf.setColumns(10);
		
		JPanel cpuPanel = new JPanel();
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbccpuPanel = new GridBagConstraints();
		gbccpuPanel.fill = GridBagConstraints.BOTH;
		gbccpuPanel.gridx = 0;
		gbccpuPanel.gridy = 1;
		hwidCpuPanel.add(cpuPanel, gbccpuPanel);
		GridBagLayout gbl_cpuPanel = new GridBagLayout();
		gbl_cpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_cpuPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_cpuPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_cpuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		cpuPanel.setLayout(gbl_cpuPanel);
		
		JLabel cpuCount = new JLabel("CPU#");
		GridBagConstraints gbc_cpuCount = new GridBagConstraints();
		gbc_cpuCount.insets = new Insets(0, 0, 5, 5);
		gbc_cpuCount.gridx = 0;
		gbc_cpuCount.gridy = 0;
		cpuPanel.add(cpuCount, gbc_cpuCount);
		
		cpuChoice = new JComboBox<>();
		cpuChoice.setEditable(false);
		GridBagConstraints gbc_cpuChoice = new GridBagConstraints();
		gbc_cpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuChoice.insets = new Insets(0, 0, 5, 5);
		gbc_cpuChoice.gridx = 1;
		gbc_cpuChoice.gridy = 0;
		cpuPanel.add(cpuChoice, gbc_cpuChoice);
		
		JLabel cpuName = new JLabel("Name");
		GridBagConstraints gbc_cpuName = new GridBagConstraints();
		gbc_cpuName.insets = new Insets(0, 0, 5, 5);
		gbc_cpuName.gridx = 2;
		gbc_cpuName.gridy = 0;
		cpuPanel.add(cpuName, gbc_cpuName);
		
		cpuNameTf = new JTextField();
		cpuNameTf.setEditable(false);
		GridBagConstraints gbc_cpuNameTf = new GridBagConstraints();
		gbc_cpuNameTf.gridwidth = 3;
		gbc_cpuNameTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuNameTf.gridx = 3;
		gbc_cpuNameTf.gridy = 0;
		cpuPanel.add(cpuNameTf, gbc_cpuNameTf);
		cpuNameTf.setColumns(10);
		
		JLabel cpuCores = new JLabel("Cores");
		GridBagConstraints gbc_cpuCores = new GridBagConstraints();
		gbc_cpuCores.insets = new Insets(0, 0, 5, 5);
		gbc_cpuCores.gridx = 0;
		gbc_cpuCores.gridy = 1;
		cpuPanel.add(cpuCores, gbc_cpuCores);
		
		cpuCoreTf = new JTextField();
		cpuCoreTf.setEditable(false);
		GridBagConstraints gbc_cpuCoreTf = new GridBagConstraints();
		gbc_cpuCoreTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuCoreTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuCoreTf.gridx = 1;
		gbc_cpuCoreTf.gridy = 1;
		cpuPanel.add(cpuCoreTf, gbc_cpuCoreTf);
		cpuCoreTf.setColumns(10);
		
		JLabel cpuThreads = new JLabel("Threads");
		GridBagConstraints gbc_cpuThreads = new GridBagConstraints();
		gbc_cpuThreads.insets = new Insets(0, 0, 5, 5);
		gbc_cpuThreads.gridx = 2;
		gbc_cpuThreads.gridy = 1;
		cpuPanel.add(cpuThreads, gbc_cpuThreads);
		
		cpuThreadTf = new JTextField();
		cpuThreadTf.setEditable(false);
		GridBagConstraints gbc_cpuThreadTf = new GridBagConstraints();
		gbc_cpuThreadTf.weightx = 2.0;
		gbc_cpuThreadTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuThreadTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuThreadTf.gridx = 3;
		gbc_cpuThreadTf.gridy = 1;
		cpuPanel.add(cpuThreadTf, gbc_cpuThreadTf);
		cpuThreadTf.setColumns(10);
		
		JLabel cpuLogicalProcessors = new JLabel("Logical Processors");
		GridBagConstraints gbc_cpuLogicalProcessors = new GridBagConstraints();
		gbc_cpuLogicalProcessors.insets = new Insets(0, 0, 5, 5);
		gbc_cpuLogicalProcessors.gridx = 4;
		gbc_cpuLogicalProcessors.gridy = 1;
		cpuPanel.add(cpuLogicalProcessors, gbc_cpuLogicalProcessors);
		
		cpuLogicProcessorTf = new JTextField();
		cpuLogicProcessorTf.setEditable(false);
		GridBagConstraints gbc_cpuLogicProcessorTf = new GridBagConstraints();
		gbc_cpuLogicProcessorTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuLogicProcessorTf.weightx = 2.0;
		gbc_cpuLogicProcessorTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuLogicProcessorTf.gridx = 5;
		gbc_cpuLogicProcessorTf.gridy = 1;
		cpuPanel.add(cpuLogicProcessorTf, gbc_cpuLogicProcessorTf);
		cpuLogicProcessorTf.setColumns(10);
		
		JLabel addressWidth = new JLabel("Address Width");
		GridBagConstraints gbc_addressWidth = new GridBagConstraints();
		gbc_addressWidth.insets = new Insets(0, 0, 5, 5);
		gbc_addressWidth.gridx = 0;
		gbc_addressWidth.gridy = 2;
		cpuPanel.add(addressWidth, gbc_addressWidth);
		
		addressWidthTf = new JTextField();
		addressWidthTf.setEditable(false);
		GridBagConstraints gbc_addressWidthTf = new GridBagConstraints();
		gbc_addressWidthTf.insets = new Insets(0, 0, 5, 5);
		gbc_addressWidthTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressWidthTf.gridx = 1;
		gbc_addressWidthTf.gridy = 2;
		cpuPanel.add(addressWidthTf, gbc_addressWidthTf);
		addressWidthTf.setColumns(10);
		
		JLabel cpuSocket = new JLabel("Socket");
		GridBagConstraints gbc_cpuSocket = new GridBagConstraints();
		gbc_cpuSocket.insets = new Insets(0, 0, 5, 5);
		gbc_cpuSocket.gridx = 2;
		gbc_cpuSocket.gridy = 2;
		cpuPanel.add(cpuSocket, gbc_cpuSocket);
		
		cpuSocketTf = new JTextField();
		cpuSocketTf.setEditable(false);
		GridBagConstraints gbc_cpuSocketTf = new GridBagConstraints();
		gbc_cpuSocketTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuSocketTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuSocketTf.gridx = 3;
		gbc_cpuSocketTf.gridy = 2;
		cpuPanel.add(cpuSocketTf, gbc_cpuSocketTf);
		cpuSocketTf.setColumns(10);
		
		JLabel cpuManufacturer = new JLabel("Manufacturer");
		GridBagConstraints gbc_cpuManufacturer = new GridBagConstraints();
		gbc_cpuManufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_cpuManufacturer.gridx = 4;
		gbc_cpuManufacturer.gridy = 2;
		cpuPanel.add(cpuManufacturer, gbc_cpuManufacturer);
		
		cpuManufacturerTf = new JTextField();
		cpuManufacturerTf.setEditable(false);
		GridBagConstraints gbc_cpuManufacturerTf = new GridBagConstraints();
		gbc_cpuManufacturerTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuManufacturerTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuManufacturerTf.gridx = 5;
		gbc_cpuManufacturerTf.gridy = 2;
		cpuPanel.add(cpuManufacturerTf, gbc_cpuManufacturerTf);
		cpuManufacturerTf.setColumns(10);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 6;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		cpuPanel.add(separator, gbc_separator);
		
		JLabel cpuExtClock = new JLabel("BCLK");
		GridBagConstraints gbc_cpuExtClock = new GridBagConstraints();
		gbc_cpuExtClock.insets = new Insets(0, 0, 5, 5);
		gbc_cpuExtClock.gridx = 0;
		gbc_cpuExtClock.gridy = 4;
		cpuPanel.add(cpuExtClock, gbc_cpuExtClock);
		
		cpuBaseClockTf = new JTextField();
		cpuBaseClockTf.setEditable(false);
		GridBagConstraints gbc_cpuBaseClockTf = new GridBagConstraints();
		gbc_cpuBaseClockTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuBaseClockTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuBaseClockTf.gridx = 1;
		gbc_cpuBaseClockTf.gridy = 4;
		cpuPanel.add(cpuBaseClockTf, gbc_cpuBaseClockTf);
		cpuBaseClockTf.setColumns(10);
		
		JLabel cpuMultiplier = new JLabel("Multiplier");
		GridBagConstraints gbc_cpuMultiplier = new GridBagConstraints();
		gbc_cpuMultiplier.insets = new Insets(0, 0, 5, 5);
		gbc_cpuMultiplier.gridx = 2;
		gbc_cpuMultiplier.gridy = 4;
		cpuPanel.add(cpuMultiplier, gbc_cpuMultiplier);
		
		multiplierTf = new JTextField();
		multiplierTf.setEditable(false);
		GridBagConstraints gbc_multiplierTf = new GridBagConstraints();
		gbc_multiplierTf.insets = new Insets(0, 0, 5, 5);
		gbc_multiplierTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_multiplierTf.gridx = 3;
		gbc_multiplierTf.gridy = 4;
		cpuPanel.add(multiplierTf, gbc_multiplierTf);
		multiplierTf.setColumns(10);
		
		JLabel cpuEffectiveClock = new JLabel("Effective Clock");
		GridBagConstraints gbc_cpuEffectiveClock = new GridBagConstraints();
		gbc_cpuEffectiveClock.insets = new Insets(0, 0, 5, 5);
		gbc_cpuEffectiveClock.gridx = 4;
		gbc_cpuEffectiveClock.gridy = 4;
		cpuPanel.add(cpuEffectiveClock, gbc_cpuEffectiveClock);
		
		effectiveClockTf = new JTextField();
		effectiveClockTf.setEditable(false);
		GridBagConstraints gbc_effectiveClockTf = new GridBagConstraints();
		gbc_effectiveClockTf.insets = new Insets(0, 0, 5, 0);
		gbc_effectiveClockTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_effectiveClockTf.gridx = 5;
		gbc_effectiveClockTf.gridy = 4;
		cpuPanel.add(effectiveClockTf, gbc_effectiveClockTf);
		effectiveClockTf.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 6;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 5;
		cpuPanel.add(separator_1, gbc_separator_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		cpuPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 6;
		cpuPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		cpuPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 6;
		cpuPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 6;
		cpuPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 6;
		cpuPanel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 7;
		cpuPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 7;
		cpuPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 7;
		cpuPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 7;
		cpuPanel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 7;
		cpuPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 5;
		gbc_textField_5.gridy = 7;
		cpuPanel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridwidth = 6;
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 8;
		cpuPanel.add(separator_2, gbc_separator_2);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 9;
		cpuPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 9;
		cpuPanel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 9;
		cpuPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 5;
		gbc_textField_7.gridy = 9;
		cpuPanel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 10;
		cpuPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 10;
		cpuPanel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 4;
		gbc_lblNewLabel_9.gridy = 10;
		cpuPanel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 5;
		gbc_textField_9.gridy = 10;
		cpuPanel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 11;
		cpuPanel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 0, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 11;
		cpuPanel.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_11.gridx = 4;
		gbc_lblNewLabel_11.gridy = 11;
		cpuPanel.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 5;
		gbc_textField_11.gridy = 11;
		cpuPanel.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		JPanel memoryPanel = new JPanel();
		tabbedPane.addTab("Memory", null, memoryPanel, null);
		
		JPanel mainBoardPanel = new JPanel();
		tabbedPane.addTab("Mainboard", null, mainBoardPanel, null);
		
		JPanel gpuPanel = new JPanel();
		tabbedPane.addTab("GPU", null, gpuPanel, null);
		
		JPanel networkPanel = new JPanel();
		tabbedPane.addTab("Network", null, networkPanel, null);
		
		JPanel storagePanel = new JPanel();
		tabbedPane.addTab("Storage", null, storagePanel, null);
		
		JPanel osPanel = new JPanel();
		tabbedPane.addTab("OS", null, osPanel, null);
		mainFrame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mainFrame.getContentPane(), tabbedPane, hwidCpuPanel, memoryPanel, mainBoardPanel, gpuPanel, networkPanel, storagePanel, osPanel, hwidPanel, cpuPanel, hwidlabel, hwidTf, cpuCount, cpuChoice, cpuCores, cpuCoreTf, cpuThreads, cpuThreadTf, cpuLogicalProcessors, cpuLogicProcessorTf, cpuName, cpuNameTf, addressWidth, addressWidthTf, cpuSocket, cpuSocketTf, cpuManufacturer, cpuManufacturerTf, cpuExtClock, cpuMultiplier, cpuEffectiveClock, cpuBaseClockTf, multiplierTf, effectiveClockTf, separator, separator_1, lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, textField, textField_1, textField_2, textField_3, textField_4, textField_5, separator_2, lblNewLabel_6, textField_6, lblNewLabel_7, textField_7, lblNewLabel_8, textField_8, lblNewLabel_9, textField_9, lblNewLabel_10, textField_10, lblNewLabel_11, textField_11}));
	}

}
