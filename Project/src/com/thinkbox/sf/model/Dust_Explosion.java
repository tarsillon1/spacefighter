package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Dust_Explosion extends Unit {
	private int z;
	private BufferedImage actor;

	public Dust_Explosion(int locX, int locY, double a, String act, int h, int th, Weapon w) {
		super(locX, locY, a, act, h, th, w);
		explode();
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void explode() {
		z += 1;
		if (z == 1) {
			try{
				AudioPlayer.getSound(Audio.dustExplode).play();
			}catch(IllegalArgumentException e){
			}catch(BufferOverflowException e){
			}
			actor = Images.dust1;
		} else if (z == 2) {
			actor = Images.dust2;
		}

		else if (z == 3) {
			actor = Images.dust3;
		}
		else if (z == 4) {
			actor = Images.dust4;
		}
		else if (z == 5) {
			actor = Images.dust5;
		}
		else if (z == 6) {
			actor = Images.dust6;
		}
		else if (z == 7) {
			actor = Images.dust7;
		}
		else if (z == 8) {
			actor = Images.dust6;
		}
		else if (z == 9) {
			actor = Images.dust5;
		}
		else if (z == 10) {
			actor = Images.dust4;
		}
		else if (z == 11) {
			actor = Images.dust3;
		}
		else if (z == 12) {
			actor = Images.dust2;
		}
		else if (z == 13) {
			actor = Images.dust1;
		}
		else if (z == 14) {
			actor = null;
			Game.getInstance().play.explosions.removeUnit(this);
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.translate(getX(), getY());
		at.scale(0.75, 0.75);
		g2d.drawImage(actor, at, null);
	}
}
