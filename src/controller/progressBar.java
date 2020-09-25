package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import clock.clockFrame;

@SuppressWarnings("serial")
public class progressBar extends JFrame implements ActionListener {

	private JProgressBar progress;
	private Timer timer;

	private final int complete = 100;

	public progressBar() {
		setTitle("Loading");

		progress = new JProgressBar();
		progress.setStringPainted(true);
		progress.setBounds(10, 10, 200, 50);
		add(progress);

		timer = new Timer(50, this);
		timer.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(235, 110);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			int curProgress = progress.getValue();
			
			timer.setDelay(new Random().nextInt(50));

			if (curProgress >= complete) {
				timer.stop();
				this.setVisible(false);
				dispose();
				new clockFrame();
				return;
			}

			progress.setValue(++curProgress);
		}
	}

}