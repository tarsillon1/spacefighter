package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;

public class AsteroidType extends Unit {
	private BufferedImage moving;
	private int money;
	public AsteroidType(double a, String act, int h, int th, Weapon w, BufferedImage m, int worth) {
		super(0, 0, a, act, h, th, w);
		moving = m;
		money = worth;
	}

	public Asteroid create(int x, int y, boolean b) {
		return new Asteroid(x, y, getAngle(), getAction(), getHealth(), getMaxHealth(), getWeapon(), 1, moving, b, money);
	}
}
