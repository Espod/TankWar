package com.espod.zhang;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Tank {
	private int x; // 坦克的x坐标
	private int y; // 坦克的y坐标
	private final static int SP = 8; // 坦克的速度
	private Direction dir = Direction.R; // 坦克的方向
	public final static int TW = 50; // 坦克的宽
	public final static int TH = 40; // 坦克的高
	private boolean live = true; // 坦克是否活着
	private boolean good = false; // 区别敌我坦克的量
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

	// 坦克的绘图
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

	// 坦克开火 
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

	// 坦克的移动
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

		// 之前这部分代码是放到draw中的，结果坦克到了边界的时候一直闪动，可能是因为那里要过一会儿才刷新
		if (x - TW / 2 < 0) {
			x = 0 + TW / 2;
		}
		if (y - TH / 2 < 30) {
			y = 30 + TH / 2; // 30是标题栏的高度
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
