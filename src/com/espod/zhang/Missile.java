package com.espod.zhang;

import java.awt.*;

public class Missile {
	private int x;
	private int y;
	private boolean live = true; // �����ڵ��Ƿ���Ч����
	private Direction dir; // �ڵ��ķ���
	private final static int SP = 12; // �ڵ����ٶ�
	public final static int MW = 10; // �ڵ��Ŀ�
	public final static int MH = 10; // �ڵ��ĸ�
	TankClient tc = null;
	
	// �ڵ����캯��
	Missile(int x, int y, Direction dir, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		this.dir = dir;
	}

	// �����ڵ�
	public void draw(Graphics g) {
		if (!live) {
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);
		move();
	}

	// �ڵ����ƶ�
	public void move() {
		switch (dir) {
		case L:
			x -= SP;
			break;
		case R:
			x += SP;
			break;
		case U:
			y -= SP;
			break;
		case D:
			y += SP;
			break;
		}
		if (x < 0 || y < 0 || x > TankClient.GW || y > TankClient.GH) {
			live = false;
			tc.missiles.remove(this);
		}
	}

	// �õ��ڵ��Ĵ�С
	public Rectangle getRect() {
		return new Rectangle(x, y, MW, MH);
	}

	// ���̹��
	public boolean hitTank(Tank t) {
		if (this.getRect().intersects(t.getRect()) && t.isLive()) {
			t.setDie();
			live = false;
			Explode e = new Explode(t.getX(), t.getY());
			tc.explodes.add(e);
			tc.missiles.remove(this);
			t.setDie();
			return true;
		}
		return false;
	}

	// �ж��ӵ��Ƿ���Ч
	public boolean isLive() {
		return live;
	}

}