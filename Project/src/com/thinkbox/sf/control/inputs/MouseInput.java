package com.thinkbox.sf.control.inputs;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.control.timers.MusicTimer;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class MouseInput extends MouseAdapter {
	public static int MOUSE_X, MOUSE_Y;
	public static Rectangle MOUSE = new Rectangle(1, 1, 1, 1);

	@Override
	public void mousePressed(MouseEvent e) {
		int mouse = e.getButton();
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
		if (mouse == MouseEvent.BUTTON1) {
			
			if(Game.getInstance().options.getOpen()){
				if(Game.getInstance().options.check1.getRec().intersects(MOUSE)){
					Game.getInstance().options.check1.setCheck();
				}
			}
			
			switch (Game.state) {
			case GAME:
				Game.getInstance().play.player.shoot();
				break;
			case MENU:
				if (rect.intersects(Game.getInstance().menu.play) && !Game.getInstance().options.getOpen()) {
					AudioPlayer.getSound(Audio.buttonClick).play();
					AudioPlayer.getSound(Audio.menuMusic).stop();
					Game.state = GameState.GAME;
					new MusicTimer(225 * 1000).start();
					Game.getInstance().play.map.spawnTile(GameConstants.STARTX, GameConstants.STARTY);
					Game.getInstance().play.map.setCurrent(GameConstants.STARTX, GameConstants.STARTY);
				}
				
				if (MOUSE.intersects(Game.getInstance().menu.options) && !Game.getInstance().options.getOpen()) {
					Game.getInstance().options.setOpen(true);
				}
				
				if (rect.intersects(Game.getInstance().menu.quit) && !Game.getInstance().options.getOpen()) {
					System.exit(0);
				}
				break;
			case PAUSE:
				break;
			case LOADING:
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		MOUSE = new Rectangle(MOUSE_X, MOUSE_Y, 1, 1);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		switch (Game.state) {
		case GAME:
			Game.getInstance().play.player.shoot();
			break;
		case MENU:
			if (MOUSE.intersects(Game.getInstance().menu.play) && !Game.getInstance().options.getOpen()) {
				AudioPlayer.getSound(Audio.buttonClick).play();
				AudioPlayer.getSound(Audio.menuMusic).stop();
				Game.state = GameState.GAME;
				new MusicTimer(225 * 1000).start();
				Game.getInstance().play.map.spawnTile(GameConstants.STARTX, GameConstants.STARTY);
				Game.getInstance().play.map.setCurrent(GameConstants.STARTX, GameConstants.STARTY);
			}
			
			if (MOUSE.intersects(Game.getInstance().menu.options) && !Game.getInstance().options.getOpen()) {
				Game.getInstance().options.setOpen(true);
			}
			
			if (MOUSE.intersects(Game.getInstance().menu.quit) && !Game.getInstance().options.getOpen()) {
				System.exit(0);
			}
			break;
		case PAUSE:
			break;
		default:
			break;
		}
	}
}
