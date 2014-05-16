package com.thinkbox.sf.control.timers;

import java.awt.Rectangle;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class CrashTimer extends Thread {
	public CrashTimer(){
		
	}

	public void run() {
		while (true) {
				try {
					Rectangle rec1 = new Rectangle(
							Game.getInstance().play.player.getX(),
							Game.getInstance().play.player.getY(),
							(int) (Game.getInstance().play.player.getActor()
									.getWidth() / GameConstants.CRASH_SCALE),
							(int) (Game.getInstance().play.player.getActor()
									.getHeight() / GameConstants.CRASH_SCALE));
					for (Unit aUnit : Game.getInstance().play.asteriods
							.getActive()) {
						Rectangle rec2 = new Rectangle(
								aUnit.getX(),
								aUnit.getY(),
								(int) (((Asteroid) aUnit).getActor().getWidth()),
								(int) (((Asteroid) aUnit).getActor()
										.getHeight()));
						if (rec1.intersects(rec2)) {
							Game.getInstance().play.player.death();
							try {
								sleep(GameConstants.END_WAIT);
							} catch (InterruptedException e) {
							}
							Game.getInstance().play.map.destroy();
							Game.state = GameState.MENU;

						}
					}
					for (Unit aUnit : Game.getInstance().play.enemyGroup
							.getActive()) {
						Rectangle rec2 = new Rectangle(
								aUnit.getX(),
								aUnit.getY(),
								(int) (((Enemy) aUnit).getActor().getWidth() / GameConstants.CRASH_SCALE),
								(int) (((Enemy) aUnit).getActor().getHeight() / GameConstants.CRASH_SCALE));
						if (rec1.intersects(rec2)) {
							((Enemy) aUnit).death();
							Game.getInstance().play.player.death();
							try {
								sleep(GameConstants.END_WAIT);
							} catch (InterruptedException e) {
							}
							Game.getInstance().play.map.destroy();
							Game.state = GameState.MENU;

						}
					}
					
				} catch (ConcurrentModificationException e) {
				} catch (NullPointerException e) {
				} catch (NoSuchElementException e) {
				}
				try {
					sleep(GameConstants.CRASH_INTERVAL);
				} catch (InterruptedException e) {
				}
		}
	}
}
