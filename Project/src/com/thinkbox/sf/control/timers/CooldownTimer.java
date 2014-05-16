package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.Timer;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class CooldownTimer extends Thread {
	public CooldownTimer() {
	}

	public void run() {
		while (true) {
			
			
			try {
				sleep(GameConstants.COOLDOWN_INTERVAL);
				Game.getInstance().play.player.cooldown();
			} catch (InterruptedException e) {
			}
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					((Enemy) aUnit).cooldown();
				}
				
				for (Timer aTimer : Game.getInstance().play.timers) {
					aTimer.tick();
				}
			} catch (ConcurrentModificationException e) {
			}

		}

	}
}
