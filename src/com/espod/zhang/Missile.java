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
		Color c = g.getColor(); // 先把颜色取出来
		g.setColor(Color.RED);
		g.fillOval(x, y, 10, 10);
		g.setColor(c); // 再把颜色恢复回去
	}
	
	
}
