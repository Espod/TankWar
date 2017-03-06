package com.espod.zhang;

import java.awt.*;

public class Explode {
	private int x;
	private int y;
	private boolean live = true;
	private int[] diameter = {4, 7, 12, 18, 26, 32, 49, 50, 52, 58, 48, 30, 14, 6};
	
	Explode(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		if(!live) return;
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		for(int i=0; i<diameter.length; i++){
			g.fillOval(x, y, diameter[i], diameter[i]);
		}
		g.setColor(c);
		live = false;
	}
	
	public boolean isLive(){
		return live;
	}
}
