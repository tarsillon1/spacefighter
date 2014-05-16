package com.thinkbox.sf.control.timers;


import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.Renderer;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Fog_Particle;
import com.thinkbox.sf.model.Unit;

public class MovementTimer extends Thread {
	Renderer gfx;

	public MovementTimer(Renderer gfx) {
		this.gfx = gfx;

	}

	public void run() {
		int resetVal = 1000;
		int i = 0;

		while (true) {
			
			try {
				sleep(GameConstants.MOVEMENT_INTERVAL);
				try {
					for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
						((Enemy) aUnit).act();
					}
				} catch (ConcurrentModificationException e) {
				}
				
				try {
					for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
						((Asteroid) aUnit).act();
					}
				} catch (ConcurrentModificationException e) {
				}
				Game.getInstance().play.player.act();
			} catch (InterruptedException e) {

			}
			++i;
			
			if (i == resetVal) {
				i = 0;
			}

		}

	}
}
