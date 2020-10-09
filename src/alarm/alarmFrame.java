package alarm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import clock.clockFrame;

@SuppressWarnings("serial")
public class alarmFrame extends JFrame implements ActionListener {

	private alarmPanel alarmTime;
	private JButton upHour;
	private JButton downHour;
	private JButton upMin;
	private JButton downMin;
	private JButton setAlarm;
	private JButton exit;

	public alarmFrame() {
		setTitle("Alarm");

		alarmTime = new alarmPanel();
		alarmTime.setBounds(0, 0, 300, 120);
		add(alarmTime);

		upHour = new JButton("+");
		upHour.setFont(new Font("Arial", Font.BOLD, 18));
		upHour.setBounds(41, 125, 45, 45);
		upHour.addActionListener(this);
		add(upHour);

		downHour = new JButton("-");
		downHour.setFont(new Font("Arial", Font.BOLD, 25));
		downHour.setBounds(41, 185, 45, 45);
		downHour.addActionListener(this);
		add(downHour);

		upMin = new JButton("+");
		upMin.setFont(new Font("Arial", Font.BOLD, 18));
		upMin.setBounds(193, 125, 45, 45);
		upMin.addActionListener(this);
		add(upMin);

		downMin = new JButton("-");
		downMin.setFont(new Font("Arial", Font.BOLD, 25));
		downMin.setBounds(193, 185, 45, 45);
		downMin.addActionListener(this);
		add(downMin);

		setAlarm = new JButton("Set Alarm");
		setAlarm.setBounds(91, 250, 97, 20);
		setAlarm.addActionListener(this);
		add(setAlarm);

		exit = new JButton("Exit");
		exit.setBounds(91, 280, 97, 20);
		exit.addActionListener(this);
		add(exit);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == upHour) {
			alarmTime.setCurTime((alarmTime.getCurTime() + 60) % 1440);
		} else if (e.getSource() == downHour) {
			alarmTime.setCurTime((alarmTime.getCurTime() - 60 + 1440) % 1440);
		} else if (e.getSource() == upMin) {
			alarmTime.setCurTime((alarmTime.getCurTime() + 1) % 1440);
		} else if (e.getSource() == downMin) {
			alarmTime.setCurTime((alarmTime.getCurTime() - 1 + 1440) % 1440);
		} else if (e.getSource() == setAlarm) {
			clockFrame previous = new clockFrame();
			previous.setAlarm();
			previous.setAlarmHour(alarmTime.getCurTime() / 60);
			previous.setAlarmMin(alarmTime.getCurTime() % 60);
			this.setVisible(false);
			dispose();
		} else if (e.getSource() == exit) {
			this.setVisible(false);
			dispose();
			new clockFrame();
		}
	}

}