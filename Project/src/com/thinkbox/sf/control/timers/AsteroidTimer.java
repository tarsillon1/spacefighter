package com.thinkbox.sf.control.timers;

import java.awt.Rectangle;
import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Unit;

public class AsteroidTimer extends Thread{
	public AsteroidTimer(){
		
	}
	
	public void run(){
		while(true){
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					Rectangle rec = new Rectangle(aUnit.getX(), aUnit.getY(), ((Asteroid) aUnit).getActor().getWidth() , ((Asteroid) aUnit).getActor().getHeight() );
					for (Unit aUnit2 : Game.getInstance().play.asteriods.getActive()) {
						Rectangle recE = new Rectangle(aUnit2.getX(), aUnit2.getY(), ((Asteroid) aUnit2).getActor().getWidth() , ((Asteroid) aUnit2).getActor().getHeight());
						if(rec.intersects(recE) && !(aUnit == aUnit2) && !(Game.getInstance().play.moved.hasUnit(aUnit) || Game.getInstance().play.moved.hasUnit(aUnit2))){
							((Asteroid) aUnit).collide();
							((Asteroid) aUnit2).collide();
							
						}
					}
				}
				}catch (ConcurrentModificationException e) {
				}catch (NullPointerException e) {
			}
			
			try {
				sleep(GameConstants.ASTEROID_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
