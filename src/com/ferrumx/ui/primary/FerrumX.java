package com.ferrumx.ui.primary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.ferrumx.ui.secondary.ExceptionUI;


public class FerrumX {

	private JFrame mainFrame;
	
	//HWID
	private JTextField hwidTf;
	//CPU
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
	private JTextField cpuVersionTf;
	private JTextField cpuCaptionTf;
	private JTextField cpuFamilyTf;
	private JTextField cpuSteppingTf;
	private JTextField cpuVirtStatusTf;
	private JTextField cpuIdTf;
	private JTextField cpuL1Tf;
	private JTextField cpuL1AsTf;
	private JTextField cpuL2Tf;
	private JTextField cpuL2AsTf;
	private JTextField cpuL3Tf;
	private JTextField cpuL3AsTf;
	//Memory
	private JTextField memoryNameTf;
	private JTextField memoryManufacturerTf;
	private JTextField memoryModelTf;
	private JTextField memoryOtherInfoTf;
	private JTextField memoryPartNumberTf;
	private JTextField memoryFormFactorTf;
	private JTextField memoryBankLabelTf;
	private JTextField memoryCapacityTf;
	private JTextField memoryWidthTf;
	private JTextField memorySpeedTf;
	private JTextField memoryConfigClockSpeedTf;
	private JTextField memoryLocatorTf;
	private JTextField memorySerialNumberTf;

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
					window.mainFrame.pack();
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
		initializeComponents();
		initializeSystemInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("FerrumX [Build v31072024 Alpha]");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(FerrumX.class.getResource("/resources/icon_main.png")));
		mainFrame.setBounds(100, 100, 600, 450);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainFrame.getContentPane().add(tabbedPane);
		
		//Initialize the CPU Panel
		JPanel hwidCpuPanel = new JPanel();
		initializeCpuPanel(tabbedPane, hwidCpuPanel);
		
		//Initialize the Memory Panel
		JPanel memoryPanel = new JPanel();
		initializeMemoryPanel(tabbedPane, memoryPanel);
		
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
		//Keyboard Pane Tab Traversal
		//mainFrame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mainFrame.getContentPane(), tabbedPane, hwidCpuPanel, memoryPanel, mainBoardPanel, gpuPanel, networkPanel, storagePanel, osPanel, hwidPanel, cpuPanel, hwidlabel, hwidTf, cpuCount, cpuChoice, cpuCores, cpuCoreTf, cpuThreads, cpuThreadTf, cpuLogicalProcessors, cpuLogicProcessorTf, cpuName, cpuNameTf, addressWidth, addressWidthTf, cpuSocket, cpuSocketTf, cpuManufacturer, cpuManufacturerTf, cpuExtClock, cpuMultiplier, cpuEffectiveClock, cpuBaseClockTf, multiplierTf, effectiveClockTf, separator, separator_1, cpuVersion, cpuFamily, cpuVirtStatus, cpuCaption, cpuStepping, cpuId, cpuVersionTf, cpuCaptionTf, cpuFamilyTf, cpuSteppingTf, cpuVirtStatusTf, cpuIdTf, separator_2, cpuLevelOneCache, cpuL1Tf, cpuL1Associativity, cpuL1AsTf, cpuLevelTwoCache, cpuL2Tf, cpuL2Associativity, cpuL2AsTf, cpuLevelThreeCache, cpuL3Tf, cpuL3Associativity, cpuL3AsTf}));
	}
	
	private void initializeCpuPanel(JTabbedPane tabbedPane, JPanel hwidCpuPanel) {
		
		tabbedPane.addTab("CPU", null, hwidCpuPanel, "Displays CPU Information");
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
		hwidTf.setForeground(Color.MAGENTA);
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
		
		JLabel cpuVersion = new JLabel("Version");
		GridBagConstraints gbc_cpuVersion = new GridBagConstraints();
		gbc_cpuVersion.anchor = GridBagConstraints.EAST;
		gbc_cpuVersion.insets = new Insets(0, 0, 5, 5);
		gbc_cpuVersion.gridx = 0;
		gbc_cpuVersion.gridy = 6;
		cpuPanel.add(cpuVersion, gbc_cpuVersion);
		
		cpuVersionTf = new JTextField();
		cpuVersionTf.setEditable(false);
		GridBagConstraints gbc_cpuVersionTf = new GridBagConstraints();
		gbc_cpuVersionTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuVersionTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuVersionTf.gridx = 1;
		gbc_cpuVersionTf.gridy = 6;
		cpuPanel.add(cpuVersionTf, gbc_cpuVersionTf);
		cpuVersionTf.setColumns(10);
		
		JLabel cpuFamily = new JLabel("Family");
		GridBagConstraints gbc_cpuFamily = new GridBagConstraints();
		gbc_cpuFamily.anchor = GridBagConstraints.EAST;
		gbc_cpuFamily.insets = new Insets(0, 0, 5, 5);
		gbc_cpuFamily.gridx = 2;
		gbc_cpuFamily.gridy = 6;
		cpuPanel.add(cpuFamily, gbc_cpuFamily);
		
		cpuFamilyTf = new JTextField();
		cpuFamilyTf.setEditable(false);
		GridBagConstraints gbc_cpuFamilyTf = new GridBagConstraints();
		gbc_cpuFamilyTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuFamilyTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuFamilyTf.gridx = 3;
		gbc_cpuFamilyTf.gridy = 6;
		cpuPanel.add(cpuFamilyTf, gbc_cpuFamilyTf);
		cpuFamilyTf.setColumns(10);
		
		JLabel cpuVirtStatus = new JLabel("Virtualization");
		GridBagConstraints gbc_cpuVirtStatus = new GridBagConstraints();
		gbc_cpuVirtStatus.anchor = GridBagConstraints.EAST;
		gbc_cpuVirtStatus.insets = new Insets(0, 0, 5, 5);
		gbc_cpuVirtStatus.gridx = 4;
		gbc_cpuVirtStatus.gridy = 6;
		cpuPanel.add(cpuVirtStatus, gbc_cpuVirtStatus);
		
		cpuVirtStatusTf = new JTextField();
		cpuVirtStatusTf.setEditable(false);
		GridBagConstraints gbc_cpuVirtStatusTf = new GridBagConstraints();
		gbc_cpuVirtStatusTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuVirtStatusTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuVirtStatusTf.gridx = 5;
		gbc_cpuVirtStatusTf.gridy = 6;
		cpuPanel.add(cpuVirtStatusTf, gbc_cpuVirtStatusTf);
		cpuVirtStatusTf.setColumns(10);
		
		JLabel cpuCaption = new JLabel("Caption");
		GridBagConstraints gbc_cpuCaption = new GridBagConstraints();
		gbc_cpuCaption.anchor = GridBagConstraints.EAST;
		gbc_cpuCaption.insets = new Insets(0, 0, 5, 5);
		gbc_cpuCaption.gridx = 0;
		gbc_cpuCaption.gridy = 7;
		cpuPanel.add(cpuCaption, gbc_cpuCaption);
		
		cpuCaptionTf = new JTextField();
		cpuCaptionTf.setEditable(false);
		GridBagConstraints gbc_cpuCaptionTf = new GridBagConstraints();
		gbc_cpuCaptionTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuCaptionTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuCaptionTf.gridx = 1;
		gbc_cpuCaptionTf.gridy = 7;
		cpuPanel.add(cpuCaptionTf, gbc_cpuCaptionTf);
		cpuCaptionTf.setColumns(10);
		
		JLabel cpuStepping = new JLabel("Stepping");
		GridBagConstraints gbc_cpuStepping = new GridBagConstraints();
		gbc_cpuStepping.anchor = GridBagConstraints.EAST;
		gbc_cpuStepping.insets = new Insets(0, 0, 5, 5);
		gbc_cpuStepping.gridx = 2;
		gbc_cpuStepping.gridy = 7;
		cpuPanel.add(cpuStepping, gbc_cpuStepping);
		
		cpuSteppingTf = new JTextField();
		cpuSteppingTf.setEditable(false);
		GridBagConstraints gbc_cpuSteppingTf = new GridBagConstraints();
		gbc_cpuSteppingTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuSteppingTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuSteppingTf.gridx = 3;
		gbc_cpuSteppingTf.gridy = 7;
		cpuPanel.add(cpuSteppingTf, gbc_cpuSteppingTf);
		cpuSteppingTf.setColumns(10);
		
		JLabel cpuId = new JLabel("ProcessorID");
		GridBagConstraints gbc_cpuId = new GridBagConstraints();
		gbc_cpuId.anchor = GridBagConstraints.EAST;
		gbc_cpuId.insets = new Insets(0, 0, 5, 5);
		gbc_cpuId.gridx = 4;
		gbc_cpuId.gridy = 7;
		cpuPanel.add(cpuId, gbc_cpuId);
		
		cpuIdTf = new JTextField();
		cpuIdTf.setEditable(false);
		GridBagConstraints gbc_cpuIdTf = new GridBagConstraints();
		gbc_cpuIdTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuIdTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuIdTf.gridx = 5;
		gbc_cpuIdTf.gridy = 7;
		cpuPanel.add(cpuIdTf, gbc_cpuIdTf);
		cpuIdTf.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridwidth = 6;
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 8;
		cpuPanel.add(separator_2, gbc_separator_2);
		
		JLabel cpuLevelOneCache = new JLabel("L1 Cache");
		GridBagConstraints gbc_cpuLevelOneCache = new GridBagConstraints();
		gbc_cpuLevelOneCache.anchor = GridBagConstraints.EAST;
		gbc_cpuLevelOneCache.insets = new Insets(0, 0, 5, 5);
		gbc_cpuLevelOneCache.gridx = 0;
		gbc_cpuLevelOneCache.gridy = 9;
		cpuPanel.add(cpuLevelOneCache, gbc_cpuLevelOneCache);
		
		cpuL1Tf = new JTextField();
		cpuL1Tf.setEditable(false);
		GridBagConstraints gbc_cpuL1Tf = new GridBagConstraints();
		gbc_cpuL1Tf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuL1Tf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL1Tf.gridx = 1;
		gbc_cpuL1Tf.gridy = 9;
		cpuPanel.add(cpuL1Tf, gbc_cpuL1Tf);
		cpuL1Tf.setColumns(10);
		
		JLabel cpuL1Associativity = new JLabel("L1 Cache Associativity");
		GridBagConstraints gbc_cpuL1Associativity = new GridBagConstraints();
		gbc_cpuL1Associativity.anchor = GridBagConstraints.EAST;
		gbc_cpuL1Associativity.insets = new Insets(0, 0, 5, 5);
		gbc_cpuL1Associativity.gridx = 4;
		gbc_cpuL1Associativity.gridy = 9;
		cpuPanel.add(cpuL1Associativity, gbc_cpuL1Associativity);
		
		cpuL1AsTf = new JTextField();
		cpuL1AsTf.setEditable(false);
		GridBagConstraints gbc_cpuL1AsTf = new GridBagConstraints();
		gbc_cpuL1AsTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuL1AsTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL1AsTf.gridx = 5;
		gbc_cpuL1AsTf.gridy = 9;
		cpuPanel.add(cpuL1AsTf, gbc_cpuL1AsTf);
		cpuL1AsTf.setColumns(10);
		
		JLabel cpuLevelTwoCache = new JLabel("L2 Cache");
		GridBagConstraints gbc_cpuLevelTwoCache = new GridBagConstraints();
		gbc_cpuLevelTwoCache.anchor = GridBagConstraints.EAST;
		gbc_cpuLevelTwoCache.insets = new Insets(0, 0, 5, 5);
		gbc_cpuLevelTwoCache.gridx = 0;
		gbc_cpuLevelTwoCache.gridy = 10;
		cpuPanel.add(cpuLevelTwoCache, gbc_cpuLevelTwoCache);
		
		cpuL2Tf = new JTextField();
		cpuL2Tf.setEditable(false);
		GridBagConstraints gbc_cpuL2Tf = new GridBagConstraints();
		gbc_cpuL2Tf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuL2Tf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL2Tf.gridx = 1;
		gbc_cpuL2Tf.gridy = 10;
		cpuPanel.add(cpuL2Tf, gbc_cpuL2Tf);
		cpuL2Tf.setColumns(10);
		
		JLabel cpuL2Associativity = new JLabel("L2 Cache Associativity");
		GridBagConstraints gbc_cpuL2Associativity = new GridBagConstraints();
		gbc_cpuL2Associativity.anchor = GridBagConstraints.EAST;
		gbc_cpuL2Associativity.insets = new Insets(0, 0, 5, 5);
		gbc_cpuL2Associativity.gridx = 4;
		gbc_cpuL2Associativity.gridy = 10;
		cpuPanel.add(cpuL2Associativity, gbc_cpuL2Associativity);
		
		cpuL2AsTf = new JTextField();
		cpuL2AsTf.setEditable(false);
		GridBagConstraints gbc_cpuL2AsTf = new GridBagConstraints();
		gbc_cpuL2AsTf.insets = new Insets(0, 0, 5, 0);
		gbc_cpuL2AsTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL2AsTf.gridx = 5;
		gbc_cpuL2AsTf.gridy = 10;
		cpuPanel.add(cpuL2AsTf, gbc_cpuL2AsTf);
		cpuL2AsTf.setColumns(10);
		
		JLabel cpuLevelThreeCache = new JLabel("L3 Cache");
		GridBagConstraints gbc_cpuLevelThreeCache = new GridBagConstraints();
		gbc_cpuLevelThreeCache.anchor = GridBagConstraints.EAST;
		gbc_cpuLevelThreeCache.insets = new Insets(0, 0, 0, 5);
		gbc_cpuLevelThreeCache.gridx = 0;
		gbc_cpuLevelThreeCache.gridy = 11;
		cpuPanel.add(cpuLevelThreeCache, gbc_cpuLevelThreeCache);
		
		cpuL3Tf = new JTextField();
		cpuL3Tf.setEditable(false);
		GridBagConstraints gbc_cpuL3Tf = new GridBagConstraints();
		gbc_cpuL3Tf.insets = new Insets(0, 0, 0, 5);
		gbc_cpuL3Tf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL3Tf.gridx = 1;
		gbc_cpuL3Tf.gridy = 11;
		cpuPanel.add(cpuL3Tf, gbc_cpuL3Tf);
		cpuL3Tf.setColumns(10);
		
		JLabel cpuL3Associativity = new JLabel("L3 Cache Associativity");
		GridBagConstraints gbc_cpuL3Associativity = new GridBagConstraints();
		gbc_cpuL3Associativity.anchor = GridBagConstraints.EAST;
		gbc_cpuL3Associativity.insets = new Insets(0, 0, 0, 5);
		gbc_cpuL3Associativity.gridx = 4;
		gbc_cpuL3Associativity.gridy = 11;
		cpuPanel.add(cpuL3Associativity, gbc_cpuL3Associativity);
		
		cpuL3AsTf = new JTextField();
		cpuL3AsTf.setEditable(false);
		GridBagConstraints gbc_cpuL3AsTf = new GridBagConstraints();
		gbc_cpuL3AsTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuL3AsTf.gridx = 5;
		gbc_cpuL3AsTf.gridy = 11;
		cpuPanel.add(cpuL3AsTf, gbc_cpuL3AsTf);
		cpuL3AsTf.setColumns(10);
	}
	
	private void initializeMemoryPanel(JTabbedPane tabbedPane, JPanel memoryPanel) {
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Memory", null, memoryPanel, "Displays Memory Information");
		GridBagLayout gbl_memoryPanel = new GridBagLayout();
		gbl_memoryPanel.columnWidths = new int[]{0, 0, 0};
		gbl_memoryPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_memoryPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_memoryPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		memoryPanel.setLayout(gbl_memoryPanel);
		
		JLabel memorySlot = new JLabel("Slot#");
		GridBagConstraints gbc_memorySlot = new GridBagConstraints();
		gbc_memorySlot.insets = new Insets(0, 0, 5, 5);
		gbc_memorySlot.anchor = GridBagConstraints.EAST;
		gbc_memorySlot.gridx = 0;
		gbc_memorySlot.gridy = 0;
		memoryPanel.add(memorySlot, gbc_memorySlot);
		
		JComboBox<String> memorySlotChoice = new JComboBox<>();
		GridBagConstraints gbc_memorySlotChoice = new GridBagConstraints();
		gbc_memorySlotChoice.insets = new Insets(0, 0, 5, 0);
		gbc_memorySlotChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_memorySlotChoice.gridx = 1;
		gbc_memorySlotChoice.gridy = 0;
		memoryPanel.add(memorySlotChoice, gbc_memorySlotChoice);
		
		JLabel memoryName = new JLabel("Name");
		GridBagConstraints gbc_memoryName = new GridBagConstraints();
		gbc_memoryName.anchor = GridBagConstraints.EAST;
		gbc_memoryName.insets = new Insets(0, 0, 5, 5);
		gbc_memoryName.gridx = 0;
		gbc_memoryName.gridy = 1;
		memoryPanel.add(memoryName, gbc_memoryName);
		
		memoryNameTf = new JTextField();
		memoryNameTf.setEditable(false);
		GridBagConstraints gbc_memoryNameTf = new GridBagConstraints();
		gbc_memoryNameTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryNameTf.gridx = 1;
		gbc_memoryNameTf.gridy = 1;
		memoryPanel.add(memoryNameTf, gbc_memoryNameTf);
		memoryNameTf.setColumns(10);
		
		JLabel memoryManufacturer = new JLabel("Manufacturer");
		GridBagConstraints gbc_memoryManufacturer = new GridBagConstraints();
		gbc_memoryManufacturer.anchor = GridBagConstraints.EAST;
		gbc_memoryManufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_memoryManufacturer.gridx = 0;
		gbc_memoryManufacturer.gridy = 2;
		memoryPanel.add(memoryManufacturer, gbc_memoryManufacturer);
		
		memoryManufacturerTf = new JTextField();
		memoryManufacturerTf.setEditable(false);
		GridBagConstraints gbc_memoryManufacturerTf = new GridBagConstraints();
		gbc_memoryManufacturerTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryManufacturerTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryManufacturerTf.gridx = 1;
		gbc_memoryManufacturerTf.gridy = 2;
		memoryPanel.add(memoryManufacturerTf, gbc_memoryManufacturerTf);
		memoryManufacturerTf.setColumns(10);
		
		JLabel memoryModel = new JLabel("Model");
		GridBagConstraints gbc_memoryModel = new GridBagConstraints();
		gbc_memoryModel.anchor = GridBagConstraints.EAST;
		gbc_memoryModel.insets = new Insets(0, 0, 5, 5);
		gbc_memoryModel.gridx = 0;
		gbc_memoryModel.gridy = 3;
		memoryPanel.add(memoryModel, gbc_memoryModel);
		
		memoryModelTf = new JTextField();
		memoryModelTf.setEditable(false);
		GridBagConstraints gbc_memoryModelTf = new GridBagConstraints();
		gbc_memoryModelTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryModelTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryModelTf.gridx = 1;
		gbc_memoryModelTf.gridy = 3;
		memoryPanel.add(memoryModelTf, gbc_memoryModelTf);
		memoryModelTf.setColumns(10);
		
		JLabel memoryOtherInfo = new JLabel("Other Identifying Info");
		GridBagConstraints gbc_memoryOtherInfo = new GridBagConstraints();
		gbc_memoryOtherInfo.anchor = GridBagConstraints.EAST;
		gbc_memoryOtherInfo.insets = new Insets(0, 0, 5, 5);
		gbc_memoryOtherInfo.gridx = 0;
		gbc_memoryOtherInfo.gridy = 4;
		memoryPanel.add(memoryOtherInfo, gbc_memoryOtherInfo);
		
		memoryOtherInfoTf = new JTextField();
		memoryOtherInfoTf.setEditable(false);
		GridBagConstraints gbc_memoryOtherInfoTf = new GridBagConstraints();
		gbc_memoryOtherInfoTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryOtherInfoTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryOtherInfoTf.gridx = 1;
		gbc_memoryOtherInfoTf.gridy = 4;
		memoryPanel.add(memoryOtherInfoTf, gbc_memoryOtherInfoTf);
		memoryOtherInfoTf.setColumns(10);
		
		JLabel memoryPartNumber = new JLabel("Part Number");
		GridBagConstraints gbc_memoryPartNumber = new GridBagConstraints();
		gbc_memoryPartNumber.anchor = GridBagConstraints.EAST;
		gbc_memoryPartNumber.insets = new Insets(0, 0, 5, 5);
		gbc_memoryPartNumber.gridx = 0;
		gbc_memoryPartNumber.gridy = 5;
		memoryPanel.add(memoryPartNumber, gbc_memoryPartNumber);
		
		memoryPartNumberTf = new JTextField();
		memoryPartNumberTf.setEditable(false);
		GridBagConstraints gbc_memoryPartNumberTf = new GridBagConstraints();
		gbc_memoryPartNumberTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryPartNumberTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryPartNumberTf.gridx = 1;
		gbc_memoryPartNumberTf.gridy = 5;
		memoryPanel.add(memoryPartNumberTf, gbc_memoryPartNumberTf);
		memoryPartNumberTf.setColumns(10);
		
		JLabel memorySerialNumber = new JLabel("Serial Number");
		GridBagConstraints gbc_memorySerialNumber = new GridBagConstraints();
		gbc_memorySerialNumber.anchor = GridBagConstraints.EAST;
		gbc_memorySerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_memorySerialNumber.gridx = 0;
		gbc_memorySerialNumber.gridy = 6;
		memoryPanel.add(memorySerialNumber, gbc_memorySerialNumber);
		
		memorySerialNumberTf = new JTextField();
		memorySerialNumberTf.setEditable(false);
		GridBagConstraints gbc_memorySerialNumberTf = new GridBagConstraints();
		gbc_memorySerialNumberTf.insets = new Insets(0, 0, 5, 0);
		gbc_memorySerialNumberTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memorySerialNumberTf.gridx = 1;
		gbc_memorySerialNumberTf.gridy = 6;
		memoryPanel.add(memorySerialNumberTf, gbc_memorySerialNumberTf);
		memorySerialNumberTf.setColumns(10);
		
		JLabel memoryFormFactor = new JLabel("Form Factor");
		GridBagConstraints gbc_memoryFormFactor = new GridBagConstraints();
		gbc_memoryFormFactor.anchor = GridBagConstraints.EAST;
		gbc_memoryFormFactor.insets = new Insets(0, 0, 5, 5);
		gbc_memoryFormFactor.gridx = 0;
		gbc_memoryFormFactor.gridy = 7;
		memoryPanel.add(memoryFormFactor, gbc_memoryFormFactor);
		
		memoryFormFactorTf = new JTextField();
		memoryFormFactorTf.setEditable(false);
		GridBagConstraints gbc_memoryFormFactorTf = new GridBagConstraints();
		gbc_memoryFormFactorTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryFormFactorTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryFormFactorTf.gridx = 1;
		gbc_memoryFormFactorTf.gridy = 7;
		memoryPanel.add(memoryFormFactorTf, gbc_memoryFormFactorTf);
		memoryFormFactorTf.setColumns(10);
		
		JLabel memoryBankLabel = new JLabel("Bank Label");
		GridBagConstraints gbc_memoryBankLabel = new GridBagConstraints();
		gbc_memoryBankLabel.anchor = GridBagConstraints.EAST;
		gbc_memoryBankLabel.insets = new Insets(0, 0, 5, 5);
		gbc_memoryBankLabel.gridx = 0;
		gbc_memoryBankLabel.gridy = 8;
		memoryPanel.add(memoryBankLabel, gbc_memoryBankLabel);
		
		memoryBankLabelTf = new JTextField();
		memoryBankLabelTf.setEditable(false);
		GridBagConstraints gbc_memoryBankLabelTf = new GridBagConstraints();
		gbc_memoryBankLabelTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryBankLabelTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryBankLabelTf.gridx = 1;
		gbc_memoryBankLabelTf.gridy = 8;
		memoryPanel.add(memoryBankLabelTf, gbc_memoryBankLabelTf);
		memoryBankLabelTf.setColumns(10);
		
		JLabel memoryCapacity = new JLabel("Capacity");
		GridBagConstraints gbc_memoryCapacity = new GridBagConstraints();
		gbc_memoryCapacity.anchor = GridBagConstraints.EAST;
		gbc_memoryCapacity.insets = new Insets(0, 0, 5, 5);
		gbc_memoryCapacity.gridx = 0;
		gbc_memoryCapacity.gridy = 9;
		memoryPanel.add(memoryCapacity, gbc_memoryCapacity);
		
		memoryCapacityTf = new JTextField();
		memoryCapacityTf.setEditable(false);
		GridBagConstraints gbc_memoryCapacityTf = new GridBagConstraints();
		gbc_memoryCapacityTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryCapacityTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryCapacityTf.gridx = 1;
		gbc_memoryCapacityTf.gridy = 9;
		memoryPanel.add(memoryCapacityTf, gbc_memoryCapacityTf);
		memoryCapacityTf.setColumns(10);
		
		JLabel memoryWidth = new JLabel("Data Width");
		GridBagConstraints gbc_memoryWidth = new GridBagConstraints();
		gbc_memoryWidth.anchor = GridBagConstraints.EAST;
		gbc_memoryWidth.insets = new Insets(0, 0, 5, 5);
		gbc_memoryWidth.gridx = 0;
		gbc_memoryWidth.gridy = 10;
		memoryPanel.add(memoryWidth, gbc_memoryWidth);
		
		memoryWidthTf = new JTextField();
		memoryWidthTf.setEditable(false);
		GridBagConstraints gbc_memoryWidthTf = new GridBagConstraints();
		gbc_memoryWidthTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryWidthTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryWidthTf.gridx = 1;
		gbc_memoryWidthTf.gridy = 10;
		memoryPanel.add(memoryWidthTf, gbc_memoryWidthTf);
		memoryWidthTf.setColumns(10);
		
		JLabel memorySpeed = new JLabel("Speed");
		GridBagConstraints gbc_memorySpeed = new GridBagConstraints();
		gbc_memorySpeed.anchor = GridBagConstraints.EAST;
		gbc_memorySpeed.insets = new Insets(0, 0, 5, 5);
		gbc_memorySpeed.gridx = 0;
		gbc_memorySpeed.gridy = 11;
		memoryPanel.add(memorySpeed, gbc_memorySpeed);
		
		memorySpeedTf = new JTextField();
		memorySpeedTf.setEditable(false);
		GridBagConstraints gbc_memorySpeedTf = new GridBagConstraints();
		gbc_memorySpeedTf.insets = new Insets(0, 0, 5, 0);
		gbc_memorySpeedTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memorySpeedTf.gridx = 1;
		gbc_memorySpeedTf.gridy = 11;
		memoryPanel.add(memorySpeedTf, gbc_memorySpeedTf);
		memorySpeedTf.setColumns(10);
		
		JLabel memoryConfigClockSpeed = new JLabel("Configured Clock Speed");
		GridBagConstraints gbc_memoryConfigClockSpeed = new GridBagConstraints();
		gbc_memoryConfigClockSpeed.anchor = GridBagConstraints.EAST;
		gbc_memoryConfigClockSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_memoryConfigClockSpeed.gridx = 0;
		gbc_memoryConfigClockSpeed.gridy = 12;
		memoryPanel.add(memoryConfigClockSpeed, gbc_memoryConfigClockSpeed);
		
		memoryConfigClockSpeedTf = new JTextField();
		memoryConfigClockSpeedTf.setEditable(false);
		GridBagConstraints gbc_memoryConfigClockSpeedTf = new GridBagConstraints();
		gbc_memoryConfigClockSpeedTf.insets = new Insets(0, 0, 5, 0);
		gbc_memoryConfigClockSpeedTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryConfigClockSpeedTf.gridx = 1;
		gbc_memoryConfigClockSpeedTf.gridy = 12;
		memoryPanel.add(memoryConfigClockSpeedTf, gbc_memoryConfigClockSpeedTf);
		memoryConfigClockSpeedTf.setColumns(10);
		
		JLabel memoryLocator = new JLabel("Device Locator");
		GridBagConstraints gbc_memoryLocator = new GridBagConstraints();
		gbc_memoryLocator.anchor = GridBagConstraints.EAST;
		gbc_memoryLocator.insets = new Insets(0, 0, 0, 5);
		gbc_memoryLocator.gridx = 0;
		gbc_memoryLocator.gridy = 13;
		memoryPanel.add(memoryLocator, gbc_memoryLocator);
		
		memoryLocatorTf = new JTextField();
		memoryLocatorTf.setEditable(false);
		GridBagConstraints gbc_memoryLocatorTf = new GridBagConstraints();
		gbc_memoryLocatorTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_memoryLocatorTf.gridx = 1;
		gbc_memoryLocatorTf.gridy = 13;
		memoryPanel.add(memoryLocatorTf, gbc_memoryLocatorTf);
		memoryLocatorTf.setColumns(10);
	}


	private void initializeSystemInfo() {
		
		try(ExecutorService exec = Executors.newCachedThreadPool()){
			Future<Boolean> initializeHardwareId = exec.submit(()->(HardwareId.initializeHardwareId(hwidTf)));
			Future<Boolean> initializeCpu = exec.submit(()-> Cpu.initializeCpu(cpuChoice, cpuNameTf, cpuCoreTf, cpuThreadTf, cpuLogicProcessorTf, cpuManufacturerTf, addressWidthTf, cpuSocketTf, cpuBaseClockTf, multiplierTf, effectiveClockTf, cpuVersionTf, cpuCaptionTf, cpuFamilyTf, cpuSteppingTf, cpuVirtStatusTf, cpuIdTf, cpuL1Tf, cpuL1AsTf, cpuL2Tf, cpuL2AsTf, cpuL3Tf, cpuL3AsTf));
			
			initializeHardwareId.get();
			initializeCpu.get();
		}
		catch (RejectedExecutionException | NullPointerException | ExecutionException | InterruptedException e) {
			new ExceptionUI("Host Gather System Info Error", e.getMessage()).setVisible(true);
			Thread.currentThread().interrupt();
		}
	}
}
