package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.model.CheckBox;

public class Options {
	public CheckBox check1 = new CheckBox(((int)(Game.frame.getWidth() / 2) + 7), 75);
	public Rectangle exit = new Rectangle((int)(Game.frame.getWidth() / 2), (int)(Game.frame.getHeight() - 50), 200, 50);
	private boolean open;
	
	public boolean getOpen(){
		return open;
	}
	
	public void setOpen(boolean b){
		open = b;
	}
	
	public Options(){
	}
	
	public void render(Graphics g){
		if(open){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.frame.getWidth(), Game.frame.getHeight());
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 45));
			g.drawString("OPTIONS", (Game.frame.getWidth() / 2) - 115, 50);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("Fog:", check1.getX() - 90, check1.getY() + 37);
			g.drawRect((int)exit.getX(), (int)exit.getY(), (int)exit.getWidth(), (int)exit.getHeight());
			check1.render(g);
		}
	}
}
