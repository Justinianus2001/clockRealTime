package com.lnhoang.controller;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
 
@SuppressWarnings("serial")
public class clockRealTime extends JFrame implements ActionListener {
	
    private JLabel labelClock;
    private JLabel labelDate;
    private Font fontClock;
    private Font fontDate;
    private JMenuBar menuBar;
    private JMenu option;
    private JMenu help;
    private JMenu exit;
    private JMenuItem changeMode;
    private JMenuItem pause;
    private JMenuItem gitHub;
    private JMenuItem about;
    private JMenuItem quit;
    private boolean run = true;
    private boolean mode24H = false;
 
    public clockRealTime() {
        setTitle("Clock");
        
        fontClock = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        fontDate = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        labelClock = new JLabel("00:00:00 AM", SwingConstants.CENTER);
        labelClock.setBounds(0, 45, 230, 30);
        labelClock.setFont(fontClock);
        add(labelClock);
        
        labelDate = new JLabel("Mon Jan 1 1900", SwingConstants.CENTER);
        labelDate.setBounds(0, 70, 230, 30);
        labelClock.setFont(fontDate);
        add(labelDate);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        option = new JMenu("Option");
        menuBar.add(option);
        
        changeMode = new JMenuItem("Change Mode");
        changeMode.addActionListener(this);
        option.add(changeMode);
        
        pause = new JMenuItem("Pause");
        pause.addActionListener(this);
        option.add(pause);
        
        help = new JMenu("Help");
        menuBar.add(help);
        
        gitHub = new JMenuItem("GitHub");
        gitHub.addActionListener(this);
        help.add(gitHub);
        
        about = new JMenuItem("About");
        about.addActionListener(this);
        help.add(about);
        
        exit = new JMenu("Exit");
        menuBar.add(exit);
        
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        exit.add(quit);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        
        setSize(250, 200);
        setLayout(null);
        setVisible(true);
        
        while (true) {
        	try {
				Thread.sleep(1000);
				while (run) {
	                if (mode24H) {
	                  	labelClock.setText(String.format("%tT", new Date()));
	                } else {
	                   	labelClock.setText(String.format("%tr", new Date()));
	                }
	                labelDate.setText(new Date().toString().substring(0, 10) + " " + new Date().toString().substring(24));
	            }
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == changeMode) {
			mode24H = !mode24H;
		} else if (e.getSource() == pause) {
			if (pause.getText().equals("Pause")) {
				pause.setText("Continue");
			} else {
				pause.setText("Pause");
			}
			run = !run;
		} else if (e.getSource() == gitHub) {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/Justinianus2001"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(about, "Author: Justinianus\nClock Real Time");
		} else if (e.getSource() == quit) {
			if (JOptionPane.showConfirmDialog(quit, "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
}