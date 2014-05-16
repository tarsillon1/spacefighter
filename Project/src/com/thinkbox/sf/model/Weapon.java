package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;

public class Weapon {
	private int damage;
	private BufferedImage actor;
	private double speed;
	private String name;
	private String sound;

	public Weapon(int d, BufferedImage a, double s, String n, String sou) {
		damage = d;
		actor = a;
		speed = s;
		name = n;
		sound = sou;
	}

	public BufferedImage getActor() {
		return actor;
	}
	
	public String getSound() {
		return sound;
	}

	public int getDamage() {
		return damage;
	}

	public double getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}
}
