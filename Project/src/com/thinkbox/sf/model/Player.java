package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.inputs.KeyInput;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Player extends Unit {
	private BufferedImage actor;
	private BufferedImage standing;
	private BufferedImage moving;
	private boolean dead = true;
	private boolean cool;
	private int counter;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Player(int locX, int locY, double a, String act, int h, int th, BufferedImage s, BufferedImage m, Weapon w) {
		super(locX, locY, a, act, h, th, w);
		standing = s;
		moving = m;
	}

	public void cooldown() {
		if (cool) {
			counter += 1;
			if (counter >= getWeapon().getSpeed()) {
				cool = false;
				counter = 0;
			}
		}
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void setStanding(BufferedImage s) {
		standing = s;
	}

	public void setMoving(BufferedImage m) {
		moving = m;
	}

	public ArrayList<Bullet> getList() {
		return bullets;
	}

	public void act() {
		// Movement
		if (KeyInput.W && isAlive()) {
			setAction("moving");
			if (getX() > MouseInput.MOUSE_X) {
				setLoc(getX() - 1, getY());
			} else if (getX() < MouseInput.MOUSE_X) {
				setLoc(getX() + 1, getY());
			}

			if (getY() > MouseInput.MOUSE_Y) {
				setLoc(getX(), getY() - 1);
			} else if (getY() < MouseInput.MOUSE_Y) {
				setLoc(getX(), getY() + 1);
			}
		} else{
			setAction("standing");
			AudioPlayer.getSound(Audio.moveSound).stop();
		}
	}

	public void shoot() {
		if (!cool && dead == true) {
			AudioPlayer.getSound(getWeapon().getSound()).play();
			Bullet b = new Bullet(getX(), getY(), MouseInput.MOUSE_X, MouseInput.MOUSE_Y, bullets);
			bullets.add(b);
			cool = true;
		}
	}

	public void hit(int x, int y) {
		Spark s = new Spark(x, y, 0, "", 0, 0, getWeapon());
		Game.getInstance().play.sparks.addUnit(s);
	}

	public void draw(Graphics g) {
		// Graphics
		if (getAction() == "standing" && isAlive()) {
			actor = standing;
		}
		if (getAction() == "moving" && isAlive()) {
			actor = moving;
		}
		try {
			for (Bullet aBullet : bullets) {
				if (!aBullet.isActive()) {
					bullets.remove(aBullet);
				}
			}
		} catch (ConcurrentModificationException e) {
		}

		Graphics2D g2d = (Graphics2D) g;
		try {
			for (Bullet aBullet : bullets) {
				if (aBullet.isActive()) {
					AffineTransform bul = new AffineTransform();
					bul.translate(aBullet.getX(), aBullet.getY());
					bul.rotate(Math.toRadians(getBetween(aBullet.getOX(), aBullet.getOY(), aBullet.getMX(),
							aBullet.getMY())));
					bul.scale(0.5, 0.5);
					bul.translate(-getWeapon().getActor().getWidth() / 2, -getWeapon().getActor().getHeight() / 2);
					g2d.drawImage(getWeapon().getActor(), bul, null);
				}
			}
			AffineTransform at = new AffineTransform();
			at.translate(getX(), getY());
			if (isAlive()) {
				at.rotate(Math.toRadians(getBetween(getX(), getY(), MouseInput.MOUSE_X, MouseInput.MOUSE_Y)));
			}
			at.scale(0.5, 0.5);
			at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
			g2d.drawImage(actor, at, null);
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		} catch (NoSuchElementException e) {
		}
	}

	@SuppressWarnings("static-access")
	public void death() {
		if (isAlive()) {
			dead = false;
			actor = null;
			Explosion boom = new Explosion(getX(), getY(), 0, "", 0, 0, getWeapon());
			Game.getInstance().play.explosions.addUnit(boom);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			actor = Images.over;
			setLoc(Game.getInstance().frame.getWidth() / 2, Game.getInstance().frame.getHeight() / 2);
			setAction("");
			AudioPlayer.getSound(Audio.gameMusic).stop();
		}
	}

	@SuppressWarnings("static-access")
	public void revive() {
		setLoc(Game.getInstance().frame.getWidth() / 2, Game.getInstance().frame.getHeight() / 2);
		setAction("standing");
		dead = true;
	}

	public boolean isAlive() {
		return dead;
	}

}
