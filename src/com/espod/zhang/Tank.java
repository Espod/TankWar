package com.espod.zhang;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Tank {
	private int x; // ̹�˵�x����
	private int y; // ̹�˵�y����
	private final static int SP = 8; // ̹�˵��ٶ�
	private Direction dir = Direction.R; // ̹�˵ķ���
	public final static int TW = 50; // ̹�˵Ŀ�
	public final static int TH = 40; // ̹�˵ĸ�
	private boolean live = true; // ̹���Ƿ����
	private boolean good = false; // �������̹�˵���
    TankClient tc = null;
    
	Tank(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	Tank(int x, int y, boolean good, TankClient tc) {
		this(x, y, tc);
		this.good = good;
	}

	// ̹�˵Ļ�ͼ
	public void draw(Graphics g) {
		if (!live) {
			return;
		}
		Color c = g.getColor();
		if (!good) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(x, y, TW, TH);
		g.setColor(c);

		switch (dir) {
		case L:
			g.drawLine(x - TW / 2, y + TH / 2, x + TW / 2, y + TH / 2);
			break;
		case R:
			g.drawLine(x + TW + TW / 2, y + TH / 2, x + TW / 2, y + TH / 2);
			break;
		case U:
			g.drawLine(x + TW / 2, y - TH / 2, x + TW / 2, y + TH / 2);
			break;
		case D:
			g.drawLine(x + TW / 2, y + TH / 2 + TH, x + TW / 2, y + TH / 2);
			break;
		}

	}

	// ̹�˿��� 
	public Missile fire(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_CONTROL) {
			int x = this.x + TW / 2 - Missile.MW / 2;
			int y = this.y + TH / 2 - Missile.MH / 2;
			Missile m = new Missile(x, y, dir, tc);
			tc.missiles.add(m);
			return m;
		}
		return null;
	}

	// ̹�˵��ƶ�
	public void move(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			dir = Direction.L;
			x -= SP;
			break;
		case KeyEvent.VK_RIGHT:
			dir = Direction.R;
			x += SP;
			break;
		case KeyEvent.VK_UP:
			dir = Direction.U;
			y -= SP;
			break;
		case KeyEvent.VK_DOWN:
			dir = Direction.D;
			y += SP;
			break;
		}

		// ֮ǰ�ⲿ�ִ����Ƿŵ�draw�еģ����̹�˵��˱߽��ʱ��һֱ��������������Ϊ����Ҫ��һ�����ˢ��
		if (x - TW / 2 < 0) {
			x = 0 + TW / 2;
		}
		if (y - TH / 2 < 30) {
			y = 30 + TH / 2; // 30�Ǳ������ĸ߶�
		}
		if (x + TW + TW / 2 > TankClient.GW) {
			x = TankClient.GW - TW - TW / 2;
		}
		if (y + TH + TH / 2 > TankClient.GH) {
			y = TankClient.GH - TH - TH / 2;
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, TW, TH);
	}

	public void setDie() {
		live = false;
	}

	public boolean isLive() {
		return live;
	}

	public int getX() {
		return x;
	}
	
	public int getY(){
		return y;
	}
}
