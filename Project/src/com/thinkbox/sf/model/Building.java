package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Building extends Unit {
	private BufferedImage actor;
	public Building(int locX, int locY, double a, String act, int h, int th, Weapon w, BufferedImage a2) {
		super(locX, locY, a, act, h, th, w);
		actor = a2;
	}
	
	public Rectangle getRec(){
		return new Rectangle(getX(), getY(), actor.getWidth(), actor.getHeight());
	}
	
	public void draw(Graphics g){
		g.drawImage(actor, getX(), getY(), null);
	}
}
