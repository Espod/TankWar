package com.espod.zhang;

import java.awt.*;

public class Missile {
	private int x;
	private int y;
	private boolean live = true; // 区别炮弹是否有效的量
	private Direction dir; // 炮弹的方向
	private final static int SP = 12; // 炮弹的速度
	public final static int MW = 10; // 炮弹的宽
	public final static int MH = 10; // 炮弹的高
	TankClient tc = null;
	
	// 炮弹构造函数
	Missile(int x, int y, Direction dir, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		this.dir = dir;
	}

	// 绘制炮弹
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

	// 炮弹的移动
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

	// 得到炮弹的大小
	public Rectangle getRect() {
		return new Rectangle(x, y, MW, MH);
	}

	// 打击坦克
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

	// 判断子弹是否还有效
	public boolean isLive() {
		return live;
	}

}