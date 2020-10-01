package alarm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class alarmPanel extends JPanel implements Runnable {

	private int panelWidth = 120;
	private int panelLength = 300;

	private int[][] xHour1 = { { 8, 18, 48, 58 }, { 5, 15, 15, 10, 5 }, { 61, 51, 51, 56, 61 },
			{ 13, 18, 48, 53, 48, 18 }, { 5, 15, 15, 10, 5 }, { 61, 51, 51, 56, 61 }, { 8, 18, 48, 58 } };
	private int[][] yHour1 = { { 5, 15, 15, 5 }, { 8, 18, 48, 53, 48 }, { 8, 18, 48, 53, 48 },
			{ 56, 51, 51, 56, 61, 61 }, { 104, 94, 64, 59, 64 }, { 104, 94, 64, 59, 64 }, { 107, 97, 97, 107 } };
	private int[][] xHour2 = { { 69, 79, 109, 119 }, { 66, 76, 76, 71, 66 }, { 122, 112, 112, 117, 122 },
			{ 74, 79, 109, 114, 109, 79 }, { 66, 76, 76, 71, 66 }, { 122, 112, 112, 117, 122 }, { 69, 79, 109, 119 } };
	private int[][] yHour2 = { { 5, 15, 15, 5 }, { 8, 18, 48, 53, 48 }, { 8, 18, 48, 53, 48 },
			{ 56, 51, 51, 56, 61, 61 }, { 104, 94, 64, 59, 64 }, { 104, 94, 64, 59, 64 }, { 107, 97, 97, 107 } };
	private int[][] xMin1 = { { 160, 170, 200, 210 }, { 157, 167, 167, 162, 157 }, { 213, 203, 203, 208, 213 },
			{ 165, 170, 200, 205, 200, 170 }, { 157, 167, 167, 162, 157 }, { 213, 203, 203, 208, 213 },
			{ 160, 170, 200, 210 } };
	private int[][] yMin1 = { { 5, 15, 15, 5 }, { 8, 18, 48, 53, 48 }, { 8, 18, 48, 53, 48 },
			{ 56, 51, 51, 56, 61, 61 }, { 104, 94, 64, 59, 64 }, { 104, 94, 64, 59, 64 }, { 107, 97, 97, 107 } };
	private int[][] xMin2 = { { 221, 231, 261, 271 }, { 218, 228, 228, 223, 218 }, { 274, 264, 264, 269, 274 },
			{ 226, 231, 261, 266, 261, 231 }, { 218, 228, 228, 223, 218 }, { 274, 264, 264, 269, 274 },
			{ 221, 231, 261, 271 } };
	private int[][] yMin2 = { { 5, 15, 15, 5 }, { 8, 18, 48, 53, 48 }, { 8, 18, 48, 53, 48 },
			{ 56, 51, 51, 56, 61, 61 }, { 104, 94, 64, 59, 64 }, { 104, 94, 64, 59, 64 }, { 107, 97, 97, 107 } };
	private boolean[][] select = new boolean[4][7];
	private int curTime = 0;
	private int[] digitTime = new int[4];

	private boolean onOff;
	private int cntChange = 0;

	public alarmPanel() {
		setMinimumSize(new Dimension(panelLength, panelWidth));
		setMaximumSize(new Dimension(panelLength, panelWidth));
		setPreferredSize(new Dimension(panelLength, panelWidth));
		setLayout(null);
		new Thread(this).start();
	}

	public int getCurTime() {
		return curTime;
	}

	public void setCurTime(int curTime) {
		this.curTime = curTime;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < 7; i++) {
			if (select[0][i]) {
				g2.fillPolygon(xHour1[i], yHour1[i], xHour1[i].length);
			}

			if (select[1][i]) {
				g2.fillPolygon(xHour2[i], yHour2[i], xHour2[i].length);
			}

			if (select[2][i]) {
				g2.fillPolygon(xMin1[i], yMin1[i], xMin1[i].length);
			}

			if (select[3][i]) {
				g2.fillPolygon(xMin2[i], yMin2[i], xMin2[i].length);
			}
		}

		if (onOff) {
			g2.fillRect(135, 30, 10, 10);
			g2.fillRect(135, 80, 10, 10);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				select = new boolean[4][7];

				digitTime[0] = (curTime / 60) / 10;
				digitTime[1] = (curTime / 60) % 10;
				digitTime[2] = (curTime % 60) / 10;
				digitTime[3] = (curTime % 60) % 10;

				for (int i = 0; i < 4; i++) {
					switch (digitTime[i]) {
					case 0:
						select[i][0] = select[i][1] = select[i][2] = select[i][4] = select[i][5] = select[i][6] = true;
						break;
					case 1:
						select[i][2] = select[i][5] = true;
						break;
					case 2:
						select[i][0] = select[i][2] = select[i][3] = select[i][4] = select[i][6] = true;
						break;
					case 3:
						select[i][0] = select[i][2] = select[i][3] = select[i][5] = select[i][6] = true;
						break;
					case 4:
						select[i][1] = select[i][2] = select[i][3] = select[i][5] = true;
						break;
					case 5:
						select[i][0] = select[i][1] = select[i][3] = select[i][5] = select[i][6] = true;
						break;
					case 6:
						select[i][0] = select[i][1] = select[i][3] = select[i][4] = select[i][5] = select[i][6] = true;
						break;
					case 7:
						select[i][0] = select[i][2] = select[i][5] = true;
						break;
					case 8:
						select[i][0] = select[i][1] = select[i][2] = select[i][3] = select[i][4] = select[i][5] = select[i][6] = true;
						break;
					case 9:
						select[i][0] = select[i][1] = select[i][2] = select[i][3] = select[i][5] = select[i][6] = true;
						break;
					}
				}

				cntChange++;
				if (cntChange == 10) {
					onOff = !onOff;
					cntChange = 0;
				}

				repaint();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}