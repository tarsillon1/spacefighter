package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class EnemyTimer extends Thread {
	public EnemyTimer() {
	}

	public void run() {
		while (true) {

			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					((Enemy) aUnit).newDirection(false);
				}
			} catch (ConcurrentModificationException e) {
			}

			try {
				sleep(GameConstants.AI_INTERVAL);
			} catch (InterruptedException e) {

			}
		}
	}
}
