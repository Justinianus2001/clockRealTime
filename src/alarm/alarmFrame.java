package alarm;

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

	public alarmFrame() {
		setTitle("Alarm");

		alarmTime = new alarmPanel();
		alarmTime.setBounds(0, 0, 300, 120);
		add(alarmTime);

		upHour = new JButton("^");
		upHour.setBounds(36, 120, 55, 55);
		upHour.addActionListener(this);
		add(upHour);

		downHour = new JButton("v");
		downHour.setBounds(36, 180, 55, 55);
		downHour.addActionListener(this);
		add(downHour);

		upMin = new JButton("^");
		upMin.setBounds(188, 120, 55, 55);
		upMin.addActionListener(this);
		add(upMin);

		downMin = new JButton("v");
		downMin.setBounds(188, 180, 55, 55);
		downMin.addActionListener(this);
		add(downMin);

		setAlarm = new JButton("Set Alarm");
		setAlarm.setBounds(91, 250, 97, 20);
		setAlarm.addActionListener(this);
		add(setAlarm);

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
		}
	}

}