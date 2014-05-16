package com.thinkbox.sf.control.timers;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;

public class MusicTimer extends Thread {
	private int interval;
	public MusicTimer(int i) {
		interval = i;
	}
	
	public void run() {
		while(true) {
			switch(Game.state){
			case GAME:
				Game.getInstance().play.playMusic();
				break;
			case MENU:
				Game.getInstance().menu.playMusic();
				break;

			case PAUSE:
				break;
			default:
				break;
			}
			try {
				sleep(interval);
			} catch (InterruptedException e) {
			}
		}
	}
}
