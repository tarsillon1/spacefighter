package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Explosion extends Unit {
	private int z;
	private BufferedImage actor;

	public Explosion(int locX, int locY, double a, String act, int h, int th, Weapon w) {
		super(locX, locY, a, act, h, th, w);
		explode();
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void explode() {
		z += 1;
		if (z == 1) {
			AudioPlayer.getSound(Audio.explode).play();
			actor = Images.explosion1;
		} else if (z == 2) {
			actor = Images.explosion2;
		}

		else if (z == 3) {
			actor = Images.explosion3;
		}

		else if (z == 4) {
			actor = null;
			Game.getInstance().play.explosions.removeUnit(this);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(actor, getX(), getY(), null);
	}
}
