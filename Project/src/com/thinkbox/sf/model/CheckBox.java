package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.constants.Images;

public class CheckBox {
	private int x;
	private int y;
	private boolean current;
	private BufferedImage actor;
	private BufferedImage check = Images.check;
	private BufferedImage noCheck = Images.noCheck;
	
	public CheckBox(int x1, int y1){
		x = x1;
		y = y1;
		actor = noCheck;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setLoc(int i, int i2){
		x = i;
		y = i2;
	}
	
	public boolean getCheck(){
		return current;
	}
	
	public void setCheck(){
		if(current == true){
			actor = noCheck;
			current = false;
		}
		else{
			actor = check;
			current = true;
		}
	}
	
	public Rectangle getRec(){
		return new Rectangle(x, y, actor.getWidth(), actor.getHeight());
	}
	
	public void render(Graphics g){
		g.drawImage(actor, x, y, null);
	}
	
}
