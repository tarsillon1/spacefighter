package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.model.CheckBox;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Options {
	public CheckBox check1 = new CheckBox(((int)(Game.frame.getWidth() / 2) + 7), 75);
	public CheckBox check2 = new CheckBox(((int)(Game.frame.getWidth() / 2) + 7), 150);
	public Rectangle exit = new Rectangle((int)(Game.frame.getWidth() / 2) - 101, (int)(Game.frame.getHeight() - 100), 200, 50);
	private boolean open;
	private boolean lastColor;
	
	public boolean getOpen(){
		return open;
	}
	
	public void setOpen(boolean b){
		open = b;
	}
	
	public Options(){
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH, Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
		return dimg;
	}
	
	public void render(Graphics g){
		if(open){
			if(Game.state == GameState.MENU){
				check1.setLoc((int)(Game.frame.getWidth() / 2) + 7, 75);
				check2.setLoc((int)(Game.frame.getWidth() / 2) + 7, 150);
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Game.frame.getWidth(), Game.frame.getHeight());
				resize(Images.optionsB, Game.frame.getWidth(), Game.frame.getHeight(), g, 0, 0);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Arial", Font.BOLD, 45));
				g.drawString("OPTIONS", (Game.frame.getWidth() / 2) - 115, 50);
				g.setFont(new Font("Arial", Font.PLAIN, 30));
				g.drawString("Fog:", check1.getX() - 90, check1.getY() + 37);
				g.drawString("Fire:", check2.getX() - 90, check2.getY() + 37);
				if (MouseInput.MOUSE.intersects(exit) && MouseInput.MOUSE != null) {
					if(lastColor == false){
						AudioPlayer.getSound(Audio.buttonHover).play();
						lastColor = true;
					}
					g.setColor(Color.darkGray);
				} else{
					g.setColor(Color.LIGHT_GRAY);
					lastColor = false;
				}
				g.drawString("Back", (int) exit.getX() + 65, (int) exit.getY() + 35);
				g.drawRect((int)exit.getX(), (int)exit.getY(), (int)exit.getWidth(), (int)exit.getHeight());
				check1.render(g);
				check2.render(g);
			}
				else if(Game.state == GameState.GAME){
					check1.setLoc((int)(Game.frame.getWidth() / 2) + 6, 75);
					check2.setLoc((int)(Game.frame.getWidth() / 2) + 6, 150);
					exit.setLocation((int)(Game.frame.getWidth() / 2) - 94, (int)(Game.frame.getHeight() - 100));
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, Game.frame.getWidth(), Game.frame.getHeight());
					resize(Images.optionsB, Game.frame.getWidth(), Game.frame.getHeight(), g, 0, 0);
					g.setColor(Color.LIGHT_GRAY);
					g.setFont(new Font("Arial", Font.BOLD, 45));
					g.drawString("OPTIONS", (Game.frame.getWidth() / 2) - 115, 50);
					g.setFont(new Font("Arial", Font.PLAIN, 30));
					g.drawString("Fog:", check1.getX() - 90, check1.getY() + 37);
					g.drawString("Fire:", check2.getX() - 90, check2.getY() + 37);
					if (MouseInput.MOUSE.intersects(exit) && MouseInput.MOUSE != null) {
						if(lastColor == false){
							AudioPlayer.getSound(Audio.buttonHover).play();
							lastColor = true;
						}
						g.setColor(Color.darkGray);
					} else{
						g.setColor(Color.LIGHT_GRAY);
						lastColor = false;
					}
					g.drawString("Back", (int) exit.getX() + 65, (int) exit.getY() + 35);
					g.drawRect((int)exit.getX(), (int)exit.getY(), (int)exit.getWidth(), (int)exit.getHeight());
					check1.render(g);
					check2.render(g);
				}
			}
		}
	}

