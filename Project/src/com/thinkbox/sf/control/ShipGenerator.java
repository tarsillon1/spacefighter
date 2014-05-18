package com.thinkbox.sf.control;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Fog_Particle;
import com.thinkbox.sf.model.Unit;

public class ShipGenerator {
	public ShipGenerator(){
	}
	
	public static double getBetween(int x1, int y1, int x2, int y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}
	
	public void generate(){
		if (Game.getInstance().options.check2.getCheck()) {
			int randomValueX = -15 + (int) (Math.random() * ((15 - (-15)) - 15));
			int randomValueY = -15 + (int) (Math.random() * ((15 - (-15)) - 15));
			int x = (int) ((Game.getInstance().play.player.getX() + 10 + randomValueX) - 35 * Math.cos(Math
					.toRadians(getBetween(Game.getInstance().play.player.getX() + 10 + randomValueX,
							Game.getInstance().play.player.getY() + 10 + randomValueY, MouseInput.MOUSE_X,
							MouseInput.MOUSE_Y))));
			int y = (int) ((Game.getInstance().play.player.getY() + 10 + randomValueY) - 35 * Math.sin(Math
					.toRadians(getBetween(Game.getInstance().play.player.getX() + 10 + randomValueX,
							Game.getInstance().play.player.getY() + 10 + randomValueY, MouseInput.MOUSE_X,
							MouseInput.MOUSE_Y))));
			new Fog_Particle(Images.fire, x, y);
		}
	}
}
