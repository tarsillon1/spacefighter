package com.thinkbox.sf.model;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.thinkbox.sf.Game;

public class Fog_Particle {
	private BufferedImage actor;
	private int x;
	private int y;
	private double factor;
	private double currentFactor = 1;
	private boolean remove;
	
	public Fog_Particle(BufferedImage a, int x1, int y1){
		x = x1;
		y = y1;
		actor = a;
		Random r = new Random();
		double randomValue = 0.005 + (0.02 - 0.005) * r.nextDouble();
		factor = randomValue;
		Game.getInstance().play.particles.add(this);
	}
	
	public void setLoc(int x1, int y1){
		x = x1;
		y= y1;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void act(){
		if(remove == true){
			Game.getInstance().play.particles.remove(this);
		}
	}
	
	public void draw(Graphics g){
		currentFactor -= factor;
		if(currentFactor <= 0){
			remove = true;
		}
		else{
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			at.translate(x, y);
			at.scale(currentFactor, currentFactor);
			at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
			g2d.drawImage(actor, at, null);
		}
	}
}
