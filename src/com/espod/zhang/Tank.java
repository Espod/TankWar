package com.espod.zhang;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
	private int x;
	private int y;
    private final static int SP = 8;
	Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		Color c = g.getColor(); // 先把颜色取出来
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
		g.setColor(c); // 再把颜色恢复回去
	}

	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			x -= SP;
			break;
		case KeyEvent.VK_RIGHT:
			x += SP;
			break;
		case KeyEvent.VK_UP:
			y -= SP;
			break;
		case KeyEvent.VK_DOWN:
			y += SP;
			break;
		}
	}
}
