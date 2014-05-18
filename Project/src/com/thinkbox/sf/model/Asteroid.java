package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;

public class Asteroid extends Unit {
	private int direction = 1;
	private BufferedImage actor;
	private BufferedImage moving;
	private Boolean active = true;
	private Boolean isNew = false;
	
	public Asteroid(int locX, int locY, double a, String act, int h, int th,
			Weapon w, int d, BufferedImage m, boolean b) {
		super(locX, locY, a, act, h, th, w);
		moving = m;
		direction = 1 + (int) (Math.random() * ((4 - 1) + 1));
		isNew = b;
	}
	
	public void setActive(boolean a){
		active = a;
	}
	public void collide(){
		Game.getInstance().play.moved.addUnit(this);
		if(direction == 1){
			direction = 4;
		}
		
		else if(direction == 2){
			direction = 1;
		}
		
		else if(direction == 3){
			direction = 2;
		}
		
		else if(direction == 4){
			direction = 3;
		}
	}
	
	public void setDirection(int wall){
		if(wall == 5){
			wall = 1;
		}
		if(direction == 1){
			if(wall == 1){
				direction = 2;
			}
			else if( wall == 2){
				direction = 4;
			}
		}
		else if(direction == 2){
			if(wall == 2){
				direction = 3;
			}
			else if( wall == 3){
				direction = 1;
			}
		}
		else if(direction == 3){
			if(wall == 3){
				direction = 4;
			}
			else if( wall == 4){
				direction = 2;
			}
		}
		else if(direction == 4){
			if(wall == 4){
				direction = 1;
			}
			else if( wall == 1){
				direction = 3;
			}
		}
	}
	
	public void draw(Graphics g){
		if(active == true){
			actor = moving;
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			at.translate(getX(), getY());
			at.rotate(Math.toRadians(getAngle()));
			at.scale(1, 1);
			at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
			g2d.drawImage(actor, at, null);
		}
		else{
			Game.getInstance().play.asteriods.removeUnit(this);
		}
	}
	
	public void setIsNew(boolean b){
		isNew = b;
	}
	
	public boolean isNew(){
		return isNew;
	}
	
	public void act() {
			setAngle(getAngle() + 1);
			if (direction == 1) {
				setLoc(getX() + 1, getY() - 1);
			}
			if (direction == 2) {
				setLoc(getX() + 1, getY() + 1);
			}
			if (direction == 3) {
				setLoc(getX() - 1, getY() + 1);
			}
			if (direction == 4) {
				setLoc(getX() - 1, getY() - 1);
			}

			if (getY() > Game.frame.getHeight()
					&& (!Game.getInstance().play.map.isMoving() )) {
				setDirection(3);
			} else if (getY() <= 0 && (!Game.getInstance().play.map.isMoving()) ) {
				setDirection(1);
			}

			if (getX() > Game.frame.getWidth()
					&& (!Game.getInstance().play.map.isMoving())) {
				setDirection(2);
			} else if (getX() <= 0 && (!Game.getInstance().play.map.isMoving())) {
				setDirection(4);
			}
	}

	public void remove() {
		active = false;
		actor = null;
	}

	public BufferedImage getActor() {
		return actor;
	}

	public int getDirection() {
		return direction;
	}

	public void death() {
		active = false;
		actor = null;
		Dust_Explosion boom = new Dust_Explosion(getX(), getY(), 0, "", 0, 0, getWeapon());
		Game.getInstance().play.dustE.addUnit(boom);
	}
	
}
