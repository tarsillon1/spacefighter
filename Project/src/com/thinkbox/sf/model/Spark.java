package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;

public class Spark extends Unit {
	private int z;
	private BufferedImage actor;

	public Spark(int locX, int locY, double a, String act, int h, int th, Weapon w) {
		super(locX, locY, a, act, h, th, w);
		spark();
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void spark() {
		z += 1;
		if (z == 1) {
			actor = Images.spark1;
		} else if (z == 2) {
			actor = Images.spark2;
		}

		else if (z == 3) {
			actor = Images.spark3;
		}

		else if (z == 4) {
			actor = null;
			Game.getInstance().play.sparks.removeUnit(this);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(actor, getX(), getY(), null);
	}
}
