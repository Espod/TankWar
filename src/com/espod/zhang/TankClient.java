package com.espod.zhang;

import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	private final static int GW = 1000;
	private final static int GH = 750;
	Image offScreenImage = null;
	Tank myTank = new Tank(80, 80);

	public void launchFrame() {
		this.setLocation(100, 0);
		this.setSize(GW, GH);
		this.setTitle("TankWar");
		this.setBackground(Color.GRAY);
		this.setVisible(true);
		this.setResizable(false);
		new Thread(new PaintThread()).start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
	}

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

	public void paint(Graphics g) {
		myTank.draw(g);
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

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
	
	private class KeyMonitor extends KeyAdapter{
		public  void keyPressed(KeyEvent e){
			myTank.KeyPressed(e);
		}
	}
}
