package com.thinkbox.sf.model;

import java.util.ArrayList;
import com.thinkbox.sf.constants.GameConstants;

public class Bullet {
	private int x;
	private int y;
	private int xO;
	private int yO;
	private int goX;
	private int goY;
	private boolean active = true;
	private int t;
	private ArrayList<Bullet> owner;

	public Bullet(int locX, int locY, int gX, int gY, ArrayList<Bullet> o) {
		x = locX;
		y = locY;
		xO = locX;
		yO = locY;
		goX = gX * GameConstants.BULLET_MULTIPLIER;
		goY = gY * GameConstants.BULLET_MULTIPLIER;
		owner = o;
	}

	public int getMX() {
		return goX;
	}

	public void setActive(boolean b) {
		active = b;
	}

	public int getMY() {
		return goY;
	}

	public int getX() {
		return x;
	}
	
	public int getOX() {
		return xO;
	}
	
	public int getOY() {
		return yO;
	}

	public boolean isActive() {
		return active;
	}

	public int getY() {
		return y;
	}

	public static double getBetween(int x1, int y1, int x2, int y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}

	public void move() {
		t += 1;
		x = (int) (xO + 1 * (t * GameConstants.BULLET_INTERVAL)
				* Math.cos(Math.toRadians(getBetween(xO, yO, goX, goY))));
		y = (int) (yO + 1 * (t * GameConstants.BULLET_INTERVAL)
				* Math.sin(Math.toRadians(getBetween(xO, yO, goX, goY))));
		try{
		if (t == GameConstants.BULLET_DESTROY) {
			active = false;
			owner.remove(this);
		}
		}catch(ArrayIndexOutOfBoundsException e){
		}
	}
}
