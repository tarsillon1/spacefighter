package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class HealthTimer extends Thread {
	public HealthTimer() {
	}

	public void run() {
		while (true) {

			try {
				sleep(GameConstants.GAMELOOP_INTERVAL);
				Game.getInstance().play.player.cooldown();
			} catch (InterruptedException e) {
			}
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					if (aUnit.getHealth() <= 0) {
						((Enemy) aUnit).death();
					}
				}
			} catch (ConcurrentModificationException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					if (aUnit.getHealth() <= 0) {
						((Asteroid) aUnit).death();
					}
				}
			} catch (ConcurrentModificationException e) {
			}

		}

	}
}
