package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Spark;
import com.thinkbox.sf.model.Unit;

public class SparkTimer extends Thread {
	public SparkTimer() {
	}

	public void run() {
		while (true) {
			try {
				sleep(GameConstants.SPARK_INTERVAL);
			} catch (InterruptedException e) {

			}
			try {
				for (Unit aUnit : Game.getInstance().play.sparks.getActive()) {
					((Spark) aUnit).spark();
				}
			} catch (ConcurrentModificationException e) {
			}
		}
	}
}
