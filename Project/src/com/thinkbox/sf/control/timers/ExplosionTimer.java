package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Explosion;
import com.thinkbox.sf.model.Unit;

public class ExplosionTimer extends Thread {
	public ExplosionTimer() {
	}

	public void run() {
		while (true) {
			try {
				sleep(GameConstants.EXPLODE_INTERVAL);
			} catch (InterruptedException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.moved.getActive()) {
					Game.getInstance().play.moved.removeUnit(aUnit);
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.explosions.getActive()) {
					((Explosion) aUnit).explode();
				}
			} catch (ConcurrentModificationException e) {
			}

		}

	}
}
