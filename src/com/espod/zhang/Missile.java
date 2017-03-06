package com.espod.zhang;

import java.awt.*;
import java.awt.event.*;

public class Missile {
	private int x;
	private int y;
	
	private final static int SP = 10;

	Missile(int x, int y,) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		Color c = g.getColor(); // �Ȱ���ɫȡ����
		g.setColor(Color.RED);
		g.fillOval(x, y, 10, 10);
		g.setColor(c); // �ٰ���ɫ�ָ���ȥ
	}
	
	
}
