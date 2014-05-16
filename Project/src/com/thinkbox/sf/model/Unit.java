package com.thinkbox.sf.model;

import com.thinkbox.sf.Game;

public class Unit {
	private int x;
	private int y;
	private int health;
	private int totalHealth;
	private double angle;
	private String action;
	private Weapon weapon;

	public Unit(int locX, int locY, double a, String act, int h, int th, Weapon w) {
		x = locX;
		y = locY;
		angle = a;
		action = act;
		health = h;
		totalHealth = th;
		weapon = w;
	}

	public void hit(int x, int y) {
		Spark s = new Spark(x, y, 0, "", 0, 0, getWeapon());
		Game.getInstance().play.sparks.addUnit(s);
	}
	
	public static double getBetween(int x1, int y1, int x2, int y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon w) {
		weapon = w;
	}

	public void setAction(String string) {
		action = string;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return totalHealth;
	}

	public double getAngle() {
		return angle;
	}

	public String getAction() {
		return action;
	}

	public void setLoc(int locX, int locY) {
		x = locX;
		y = locY;
	}

	public void setAngle(double a) {
		angle = a;
	}

	public void setHealth(int h) {
		health = h;
	}

	public void setMaxHealth(int h) {
		totalHealth = h;
	}
}
