package com.egg.system.report;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.egg.system.currentuser.User;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultCaret;

public class ReportWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static ReportWindow instance = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportWindow frame = ReportWindow.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Create the instance; make it singleton
	public static ReportWindow getInstance() {
		synchronized(ReportWindow.class) {
			if(instance==null)
				instance = new ReportWindow();
		}
		return instance;
	}
	
	/**
	 * Create the frame.
	 */
	private ReportWindow() {
		setTitle("WSIL Report GUI Beta Build v0.4");
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
		
		JLabel currentOperation = new JLabel("READY");
		currentOperation.setHorizontalAlignment(SwingConstants.CENTER);
		currentOperation.setFont(new Font("Consolas", Font.PLAIN, 14));
		currentOperation.setBounds(10, 40, 414, 23);
		contentPane.add(currentOperation);
		
		JTextArea errorDisplay = new JTextArea();
		errorDisplay.setWrapStyleWord(true);
		errorDisplay.setLineWrap(true);
		errorDisplay.setForeground(new Color(0, 153, 255));
		errorDisplay.setFont(new Font("Consolas", Font.PLAIN, 13));
		errorDisplay.setBackground(UIManager.getColor("Actions.GreyInline"));
		errorDisplay.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)errorDisplay.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
		
		
		JScrollPane errorDisplayScroll = new JScrollPane(errorDisplay);
		errorDisplayScroll.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		errorDisplayScroll.setBounds(10, 97, 414, 127);
		errorDisplayScroll.setAutoscrolls(true);
		errorDisplayScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		errorDisplayScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(errorDisplayScroll);
		
		JButton btnShowReport = new JButton("Show Report");
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(User.getHome()+"\\Desktop\\Report.txt"));
				} catch (IOException | NullPointerException | IllegalArgumentException | UnsupportedOperationException | SecurityException e0) {
					errorDisplay.setText("SHOW REPORT ERROR: "+e0.getMessage());
				}
			}
		});
		btnShowReport.setBounds(224, 11, 117, 23);
		contentPane.add(btnShowReport);
		
		JButton mainOperation = new JButton("Generate");
		mainOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar_1.setVisible(false);
				progressBar.setVisible(true);
				errorDisplay.setText("");
				AIOReportGeneration.generate(progressBar, currentOperation, mainOperation, errorDisplay, btnShowReport);
			}
		});
		mainOperation.setBounds(95, 11, 117, 23);
		contentPane.add(mainOperation);	
		
	}
}
