package clock;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import alarm.alarmFrame;

@SuppressWarnings("serial")
public class clockFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener, Runnable {

	private JLabel labelClock;
	private JLabel labelDate;
	private Font fontClock;
	private Font fontDate;
	private JMenuBar menuBar;
	private JMenu option;
	private JMenu help;
	private JMenu exit;
	private JMenuItem changeMode;
	private JMenuItem setupAlarm;
	private JMenuItem checkAlarm;
	private JMenuItem pause;
	private JMenuItem gitHub;
	private JMenuItem about;
	private JMenuItem quit;
	private clockPanel clockAnalog;

	private AudioInputStream stream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;

	private boolean running = true;
	private boolean mode24H = false;
	private boolean alarm = false;
	private int alarmHour = 0;
	private int alarmMin = 0;

	public clockFrame() {
		setTitle("Clock");

		try {
			stream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\alarm.wav"));
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		clockAnalog = new clockPanel();
		clockAnalog.setBounds(40, 40, 150, 150);
		add(clockAnalog);

		fontClock = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		fontDate = new Font(Font.SANS_SERIF, Font.BOLD, 20);

		labelClock = new JLabel("00:00:00 AM", SwingConstants.CENTER);
		labelClock.setBounds(0, 205, 230, 30);
		labelClock.setFont(fontClock);
		add(labelClock);

		labelDate = new JLabel("Mon Jan 1 1900", SwingConstants.CENTER);
		labelDate.setBounds(0, 230, 230, 30);
		labelClock.setFont(fontDate);
		add(labelDate);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		option = new JMenu("Option");
		menuBar.add(option);

		changeMode = new JMenuItem("Change Mode");
		changeMode.addActionListener(this);
		option.add(changeMode);

		setupAlarm = new JMenuItem("Setup Alarm");
		setupAlarm.addActionListener(this);
		option.add(setupAlarm);

		checkAlarm = new JMenuItem("Check Alarm");
		checkAlarm.addActionListener(this);
		option.add(checkAlarm);

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

		addMouseListener(this);
		addMouseMotionListener(this);

		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		new Thread(this).start();
	}

	public void setRunning() {
		this.running = !this.running;
	}

	public void setAlarm() {
		this.alarm = true;
	}

	public void setAlarmHour(int alarmHour) {
		this.alarmHour = alarmHour;
	}

	public void setAlarmMin(int alarmMin) {
		this.alarmMin = alarmMin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == changeMode) {
			mode24H = !mode24H;
		} else if (e.getSource() == setupAlarm) {
			this.setVisible(false);
			new alarmFrame();
		} else if (e.getSource() == checkAlarm) {
			if (alarm) {
				JOptionPane.showMessageDialog(checkAlarm, "Alarm setup at " + alarmHour + "H" + alarmMin + "M");
			} else {
				JOptionPane.showMessageDialog(checkAlarm, "No alarm setup !");
			}
		} else if (e.getSource() == pause) {
			if (pause.getText().equals("Pause")) {
				pause.setText("Continue");
			} else {
				pause.setText("Pause");
			}
			this.setRunning();
			clockAnalog.setRunning();
		} else if (e.getSource() == gitHub) {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/Justinianus2001"));
			} catch (IOException | URISyntaxException E) {
				E.printStackTrace();
			}
		} else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(about, "Author: Justinianus\nClock using Java Swing");
		} else if (e.getSource() == quit) {
			if (JOptionPane.showConfirmDialog(quit, "Are you sure ?", "Quit",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Enter Console");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Exit Console");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				while (running) {
					if (alarm && alarmHour == Calendar.getInstance().get(Calendar.HOUR)
							&& alarmMin == Calendar.getInstance().get(Calendar.MINUTE)) {
						clip.start();
						if (JOptionPane.showConfirmDialog(this,
								"Ring Ring Ring !!!\nYes to set alarm 5 minutes later, No to turn off", "Alarm !!!",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							alarmMin = Calendar.getInstance().get(Calendar.MINUTE) + 5;
							alarmHour = Calendar.getInstance().get(Calendar.HOUR) + alarmMin / 60;
							alarmMin %= 60;
						} else {
							alarm = false;
						}
						clip.close();
					}
					if (mode24H) {
						labelClock.setText(String.format("%tT", new Date()));
					} else {
						labelClock.setText(String.format("%tr", new Date()));
					}
					labelDate.setText(
							new Date().toString().substring(0, 10) + " " + new Date().toString().substring(24));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}