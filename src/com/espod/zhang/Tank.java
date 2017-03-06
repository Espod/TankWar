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
		Color c = g.getColor(); // �Ȱ���ɫȡ����
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
		g.setColor(c); // �ٰ���ɫ�ָ���ȥ
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
