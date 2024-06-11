package com.ferrumx.system.report;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ferrumx.system.currentuser.User;
import com.ferrumx.system.logger.ErrorLog;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultCaret;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class ReportWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static ReportWindow instance = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
		} catch (Exception e) {
			ErrorLog l = new ErrorLog();
			l.log(e.getMessage());
		}
		EventQueue.invokeLater(() -> {
			  try {
			    ReportWindow frame = ReportWindow.getInstance();
			    frame.setVisible(true);
			  } catch (Exception e) {
			    ErrorLog l = new ErrorLog();
			    l.log(e.getMessage());
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReportWindow.class.getResource("/resources/icon_main.png")));
		setTitle("FerrumX Report Tool v1.2.2");
		setResizable(false);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		errorDisplay.setForeground(Color.YELLOW);
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
		
		JRadioButton rdbtnDetailed = new JRadioButton("Detailed");
		rdbtnDetailed.setFont(new Font("Arial", Font.ITALIC, 11));
		rdbtnDetailed.setSelected(true);
		rdbtnDetailed.setBounds(137, 10, 71, 24);
		contentPane.add(rdbtnDetailed);
		
		JRadioButton rdbtnSummary = new JRadioButton("Summarized");
		rdbtnSummary.setFont(new Font("Arial", Font.ITALIC, 11));
		rdbtnSummary.setBounds(210, 10, 87, 24);
		contentPane.add(rdbtnSummary);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDetailed);
		bg.add(rdbtnSummary);
		
		JButton btnShowReport = new JButton("Show Report");
		btnShowReport.addActionListener(e-> {
				try {
					String uname = User.getUsername();
					if(rdbtnDetailed.isSelected())
						Desktop.getDesktop().open(new File(uname+"-FerrumX-Detailed-Report.txt"));
					else if(rdbtnSummary.isSelected())
						Desktop.getDesktop().open(new File(uname+"-FerrumX-Summary-Report.txt"));
				} catch (IOException | NullPointerException | IllegalArgumentException | UnsupportedOperationException | SecurityException e0) {
					errorDisplay.setText("SHOW REPORT ERROR: "+e0.getMessage());
				}
		});
		
		btnShowReport.setBounds(305, 11, 117, 23);
		contentPane.add(btnShowReport);
		
		JButton detailedReport = new JButton("Generate");
		detailedReport.addActionListener(e-> {
				progressBar_1.setVisible(false);
				progressBar.setVisible(true);
				errorDisplay.setText("");
				
				if(rdbtnDetailed.isSelected())
					DetailedReportGeneration.generate(progressBar, currentOperation, detailedReport, errorDisplay, btnShowReport);
				else if(rdbtnSummary.isSelected())
					SummaryReportGeneration.generate(progressBar, currentOperation, detailedReport, errorDisplay, btnShowReport);
		});
		detailedReport.setBounds(12, 11, 117, 23);
		contentPane.add(detailedReport);
		
	}
}
