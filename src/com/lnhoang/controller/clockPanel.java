package com.lnhoang.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Calendar;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class clockPanel extends JPanel implements Runnable {
	
	private boolean run = true;
	private int sizeClock = 150;
	private int xCenterClock = 75;
	private int yCenterClock = 75;
	private int rClock = 75;
	private int dCenterDot = 8;
	private int dBigDot = 6;
	private int dSmallDot = 4;
	private int secondLength = sizeClock - 95;
	private int minuteLength = sizeClock - 105;
	private int hourLength = sizeClock - 115;
	private int curSecond = 0;
	private int curMinute = 0;
	private int curHour = 0;
	private int xCurSecond;
	private int yCurSecond;
	private int xCurMinute;
	private int yCurMinute;
	private int xCurHour;
	private int yCurHour;
	
	public clockPanel() {
		setMinimumSize(new Dimension(sizeClock, sizeClock));
		setMaximumSize(new Dimension(sizeClock, sizeClock));
		setPreferredSize(new Dimension(sizeClock, sizeClock));
		setLayout(null);
		new Thread(this).start();
	}
	
	public void setRun() {
		run = !run;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setBackground(Color.WHITE);
		g2.setColor(Color.GRAY);
		g2.clearRect(xCenterClock - rClock, yCenterClock - rClock, sizeClock + 1, sizeClock + 1);
		
		g2.fillOval(xCenterClock - dCenterDot / 2, yCenterClock - dCenterDot / 2, dCenterDot, dCenterDot);
		
		for (int i = 0; i < 60; i++) {
			Point dotCoordinate = distance(i, rClock - 10);
			
			if (i % 5 == 0) {
				g2.fillOval(dotCoordinate.x - dBigDot / 2, dotCoordinate.y - dBigDot / 2,
						dBigDot, dBigDot);
			} else {
				g2.fillOval(dotCoordinate.x - dSmallDot / 2, dotCoordinate.y - dSmallDot / 2,
						dSmallDot, dSmallDot);
			}
		}
		
		g2.setStroke(new BasicStroke(1));
		g2.drawLine(sizeClock / 2, sizeClock / 2, xCurSecond, yCurSecond);
		
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(sizeClock / 2, sizeClock / 2, xCurMinute, yCurMinute);
		
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(sizeClock / 2, sizeClock / 2, xCurHour, yCurHour);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				while (run) {
					curSecond = Calendar.getInstance().get(Calendar.SECOND);
					curMinute = Calendar.getInstance().get(Calendar.MINUTE);
					curHour = Calendar.getInstance().get(Calendar.HOUR);
					
					xCurSecond = distance(curSecond, secondLength).x;
					yCurSecond = distance(curSecond, secondLength).y;
					
					xCurMinute = distance(curMinute + curSecond / 60, minuteLength).x;
					yCurMinute = distance(curMinute + curSecond / 60, minuteLength).y;
					
					xCurHour = distance(curHour * 5 + curHour / 12 + curMinute * 5 / 60, hourLength).x;
					yCurHour = distance(curHour * 5 + curHour / 12 + curMinute * 5 / 60, hourLength).y;
					
					repaint();
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Point distance(int step, int radius) {
		double alpha = 2 * Math.PI * (step - 15) / 60;
	    int x = (int)(sizeClock / 2 + radius * Math.cos(alpha));
	    int y = (int)(sizeClock / 2 + radius * Math.sin(alpha));
	    return new Point(x, y);
	}

}