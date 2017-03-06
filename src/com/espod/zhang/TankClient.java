package com.espod.zhang;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
	public final static int GW = 1000; // 游戏界面的宽度
	public final static int GH = 750; // 游戏界面的高度
	Image offScreenImage = null;
	Tank myTank = new Tank(80, 80, true, this);
	List<Explode> explodes = new ArrayList<Explode>();
	List<Tank> enemyTanks = new ArrayList<Tank>();
	List<Missile> missiles = new ArrayList<Missile>();

	// 初始化界面
	public void launchFrame() {
		this.setLocation(100, 0);
		this.setSize(GW, GH);
		this.setTitle("TankWar");
		this.setBackground(Color.GRAY);
		this.setVisible(true);
		this.setResizable(false);
		for (int i = 0; i < 6; i++) {
			enemyTanks.add(new Tank(30 + i * 160, 40, this));
		}
		new Thread(new PaintThread()).start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
	}

	// 重新绘制界面
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GW, GH);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GRAY);
		gOffScreen.fillRect(0, 0, GW, GH);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	// 绘制界面
	public void paint(Graphics g) {
		for (int i = 0; i < enemyTanks.size(); i++) {
			Tank t = enemyTanks.get(i);
			if (t.isLive()) {
				t.draw(g);
			}else{
				enemyTanks.remove(t);
			}
		}

		myTank.draw(g);

		g.drawString("missiles count:" + missiles.size(), 10, 50);
		g.drawString("explodes count:" + explodes.size(), 10, 70);
		g.drawString("tanks    count:" + enemyTanks.size(), 10, 90);

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			for(int j = 0; j < enemyTanks.size(); j++){
				Tank t = enemyTanks.get(j);
				m.hitTank(t);
			}
			if (m.isLive()) {
				m.draw(g);
			} else {
				missiles.remove(m);
			}
		}

		for (int j = 0; j < explodes.size(); j++) {
			Explode e = explodes.get(j);
			if (e.isLive()) {
				e.draw(g);
			} else {
				explodes.remove(e);
			}
		}
	}

	// 主函数
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

	// 绘图线程类
	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 键盘监听器类
	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			myTank.move(e);
		}

		public void keyReleased(KeyEvent e) {
			myTank.fire(e);
		}
	}

}
