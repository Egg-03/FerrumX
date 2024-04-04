package com.egg.system.report;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;

public class ReportWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportWindow frame = new ReportWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReportWindow() {
		setTitle("WSIL Report GUI Alpha Build v0.2");
		setResizable(false);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 72, 414, 14);
		progressBar.setVisible(false);
		contentPane.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setIndeterminate(true);
		progressBar_1.setBounds(10, 72, 414, 14);
		progressBar_1.setVisible(true);
		contentPane.add(progressBar_1);
		
		JLabel currentOperation = new JLabel("");
		currentOperation.setBounds(10, 40, 414, 23);
		contentPane.add(currentOperation);
		
		JTextArea errorDisplay = new JTextArea();
		errorDisplay.setWrapStyleWord(true);
		errorDisplay.setToolTipText("Error Log");
		errorDisplay.setLineWrap(true);
		errorDisplay.setForeground(Color.RED);
		errorDisplay.setFont(new Font("Consolas", Font.PLAIN, 13));
		errorDisplay.setBackground(Color.LIGHT_GRAY);
		errorDisplay.setEditable(false);
		
		
		JScrollPane errorDisplayScroll = new JScrollPane(errorDisplay);
		errorDisplayScroll.setBounds(10, 97, 414, 127);
		errorDisplayScroll.setAutoscrolls(true);
		errorDisplayScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		errorDisplayScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(errorDisplayScroll);
		
		
		JButton mainOperation = new JButton("Generate");
		mainOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar_1.setVisible(false);
				progressBar.setVisible(true);
				errorDisplay.setText("");
				AIOReportGeneration.generate(progressBar, currentOperation, mainOperation, errorDisplay);
			}
		});
		mainOperation.setBounds(162, 11, 108, 23);
		contentPane.add(mainOperation);	
		
	}
}
