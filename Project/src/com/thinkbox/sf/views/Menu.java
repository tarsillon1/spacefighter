package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Menu {
	public Rectangle play, options, quit;
	public boolean[] lastColor = new boolean[3];
	public Menu() {
		int fillerY = 150;
		play = new Rectangle(GameConstants.CENTER_X + 220, fillerY, 200, 50);
		options = new Rectangle(GameConstants.CENTER_X + 220, fillerY += 60, 200, 50);
		quit = new Rectangle(GameConstants.CENTER_X + 220, fillerY += 60, 200, 50);
	}

	public void drawButton(Graphics g, Rectangle rect, String text, int offsetX, int n) {
		Font tempFont = new Font("Arial", Font.BOLD, 45);
		g.setFont(tempFont);
		if (MouseInput.MOUSE.intersects(rect) && MouseInput.MOUSE != null && !Game.getInstance().options.getOpen()) {
			if(lastColor[n] == false){
				AudioPlayer.getSound(Audio.buttonHover).play();
				lastColor[n] = true;
			}
			g.setColor(Color.BLUE);
		} else{
			g.setColor(Color.GREEN);
			lastColor[n] = false;
		}
		
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
		g.drawString(text, rect.x + offsetX, rect.y + 42);
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
		g.drawImage(Images.backround, 0, 0, null);
		g.drawImage(Images.title, 33, 15, null);
		g.drawImage(Images.play, 230, 142, null);
		g.drawImage(Images.options, 217, 210, null);
		g.drawImage(Images.exit, 242, 262, null);
		drawButton(g, play, "", 52, 0);
		drawButton(g, options, "", 15, 1);
		drawButton(g, quit, "", 55, 2);

	}
	
	public void playMusic() {
		AudioPlayer.getSound(Audio.menuMusic).loop();
	}
}
