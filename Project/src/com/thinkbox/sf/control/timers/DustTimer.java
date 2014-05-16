package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Dust_Explosion;
import com.thinkbox.sf.model.Unit;

public class DustTimer extends Thread {
	public DustTimer() {
	}

	public void run() {
		while (true) {
			try {
				sleep(GameConstants.DUST_INTERVAL);
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
				for (Unit aUnit : Game.getInstance().play.dustE.getActive()) {
					((Dust_Explosion) aUnit).explode();
				}
			} catch (ConcurrentModificationException e) {
			}

		}

	}
}
