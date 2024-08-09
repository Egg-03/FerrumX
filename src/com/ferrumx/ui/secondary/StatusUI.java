package com.ferrumx.ui.secondary;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class StatusUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel hardwareLabel;
	private JLabel cpuLabel;
	private JLabel networkLabel;
	private JLabel osLabel;
	private JLabel memoryLabel;
	private JLabel mainboardLabel;
	private JLabel gpuLabel;
	private JLabel storageLabel;

	/**
	 * Create the frame.
	 */
	public StatusUI(String title, String message) {
		setResizable(false);
		SwingUtilities.invokeLater(() -> initialize(title, message));
	}

	private void initialize(String title, String message) {
		JPanel contentPane;
		setVisible(true);
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatusUI.class.getResource("/resources/icon_main.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		statusPanel.setBounds(10, 11, 414, 139);
		getContentPane().add(statusPanel);
		statusPanel.setLayout(null);

		JLabel infoLabel = new JLabel(message);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(10, 11, 394, 24);
		statusPanel.add(infoLabel);

		hardwareLabel = new JLabel("HWID");
		hardwareLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/HWID_16x16.png")));
		hardwareLabel.setBounds(20, 47, 85, 20);
		statusPanel.add(hardwareLabel);

		cpuLabel = new JLabel("CPU");
		cpuLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/CPU_16x16.png")));
		cpuLabel.setBounds(20, 79, 85, 20);
		statusPanel.add(cpuLabel);

		networkLabel = new JLabel("Network");
		networkLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/Network_16x16.png")));
		networkLabel.setBounds(325, 47, 79, 20);
		statusPanel.add(networkLabel);

		osLabel = new JLabel("OS");
		osLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/OS_16x16.png")));
		osLabel.setBounds(325, 79, 79, 20);
		statusPanel.add(osLabel);

		memoryLabel = new JLabel("Memory");
		memoryLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/RAM_16x16.png")));
		memoryLabel.setBounds(115, 47, 93, 20);
		statusPanel.add(memoryLabel);

		mainboardLabel = new JLabel("Mainboard");
		mainboardLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/Mainboard_16x16.png")));
		mainboardLabel.setBounds(218, 47, 97, 20);
		statusPanel.add(mainboardLabel);

		gpuLabel = new JLabel("GPU");
		gpuLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/GPU_32x32.png")));
		gpuLabel.setBounds(115, 79, 93, 20);
		statusPanel.add(gpuLabel);

		storageLabel = new JLabel("Storage");
		storageLabel.setIcon(new ImageIcon(StatusUI.class.getResource("/resources/tab_icons/Storage_16x16.png")));
		storageLabel.setBounds(218, 79, 97, 20);
		statusPanel.add(storageLabel);
	}

	public void setHardwareLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.hardwareLabel.setText("HWID ✓");
		}

	}

	public void setCpuLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.cpuLabel.setText("CPU ✓");
		}
	}

	public void setNetworkLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.networkLabel.setText("Network ✓");
		}
	}

	public void setOsLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.osLabel.setText("OS ✓");
		}
	}

	public void setMemoryLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.memoryLabel.setText("Memory ✓");
		}
	}

	public void setMainboardLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.mainboardLabel.setText("Mainboard ✓");
		}
	}

	public void setGpuLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.gpuLabel.setText("GPU ✓");
		}
	}

	public void setStorageLabel(Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			this.storageLabel.setText("Storage ✓");
		}
	}

}
