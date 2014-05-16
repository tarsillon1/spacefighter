package com.thinkbox.sf.control;

import java.awt.Rectangle;
import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class Colisions extends Thread {
	public Colisions() {
	}

	public void run() {
		while (true) {
			try {
				sleep(GameConstants.COLIDE_INTERVAL);
			} catch (InterruptedException e1) {
			}
			
			
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					Rectangle rec = new Rectangle(aUnit.getX(), aUnit.getY(), ((Enemy) aUnit).getActor().getWidth(),
							((Enemy) aUnit).getActor().getHeight());
					for (Unit aUnit2 : Game.getInstance().play.enemyGroup.getActive()) {
						Rectangle rec2 = new Rectangle(aUnit2.getX(), aUnit2.getY(), ((Enemy) aUnit2).getActor()
								.getWidth(), ((Enemy) aUnit2).getActor().getHeight());
						if (rec.intersects(rec2) && aUnit.getX() + aUnit.getY() != aUnit2.getX() + aUnit2.getY()) {
							((Enemy) aUnit).newDirection(true);
						}
					}
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			}
		}
	}
}
