package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;

public class Load {
	private static int width = 540;
	private static int numResources = 6;
	private static int loadAdd = width / numResources;
	private static int loadStatus = 0;
	private static String msg = "Loading resources...";
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH, Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
		return dimg;
	}
	
	public static void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		resize(Images.loading, GameConstants.WIDTH, GameConstants.HEIGHT, g, 0, 0);
		g.drawImage(Images.logo, 0, 0, null);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(49, 405, width, 21);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawString(msg, 51, 395);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(50, 406, loadStatus - 1, 20);
	}
	
	public static void loadMore(){
		loadStatus += loadAdd;
	}
	
	public static void setMessage(String msg){
		Load.msg = msg;
	}
}
