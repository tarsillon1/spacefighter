package com.thinkbox.sf.control.inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class KeyInput extends KeyAdapter {
	public static boolean W = false;

	public KeyInput() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (Game.state) {
		case GAME:
			if (key == KeyEvent.VK_W && Game.getInstance().play.player.isAlive()) {
				if(W == false)
					AudioPlayer.getSound(Audio.moveSound).loop();
				W = true;
			}
			if (key == KeyEvent.VK_O && Game.getInstance().play.player.isAlive()) {
				if(Game.getInstance().options.getOpen()){
					Game.getInstance().options.setOpen(false);
				}
				else
					Game.getInstance().options.setOpen(true);
			}
			break;
		case MENU:
			break;
		case PAUSE:
			break;
		default:
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (Game.state) {
		case GAME:
			if (key == KeyEvent.VK_W) {
				W = false;
			}
			break;
		case MENU:
			break;
		case PAUSE:
			break;
		default:
			break;

		}
	}
}
