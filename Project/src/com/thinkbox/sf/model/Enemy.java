package com.thinkbox.sf.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Enemy extends Unit {
	private BufferedImage actor;
	private BufferedImage standing;
	private BufferedImage moving;
	private boolean cool;
	private boolean stay;
	private int counter;
	private int direction = 1;
	private int accuracy;
	private boolean active;
	private boolean isNew;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Enemy(int locX, int locY, double a, String act, int h, int th, BufferedImage s, BufferedImage m, Weapon w,
			int ac, boolean b) {
		super(locX, locY, a, act, h, th, w);
		standing = s;
		moving = m;
		accuracy = ac;
		isNew = b;
	}

	public void newDirection(boolean s) {
		int random;
		if (s == false) {
			random = 1 + (int) (Math.random() * ((GameConstants.CHANCE - 1) + 1));
		} else {
			random = 1 + (int) (Math.random() * ((5 - 1) + 1));
		}

		int z = direction;
		if (z == 9) {

			if (Math.abs(getAngle()
					- getBetween(getX(), getY(), Game.getInstance().play.player.getX(),
							Game.getInstance().play.player.getY())) <= 2
					&& 0 <= getBetween(getX(), getY(), Game.getInstance().play.player.getX(),
							Game.getInstance().play.player.getY())) {
				direction = 1 + (int) (Math.random() * ((9 - 1) + 1));
				stay = true;
			}

			if (Math.abs(getAngle()
					- (360 + getBetween(getX(), getY(), Game.getInstance().play.player.getX(),
							Game.getInstance().play.player.getY()))) <= 2
					&& 0 > getBetween(getX(), getY(), Game.getInstance().play.player.getX(),
							Game.getInstance().play.player.getY())) {
				direction = 1 + (int) (Math.random() * ((9 - 1) + 1));
				stay = true;
			}

		} else if (random > 5) {
			direction = 9;
		}
		if (z == 1) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 8;
			} else if (z == 1) {
				direction = 2;
			} else if (random == 3) {
				direction = 2;
			}
		}

		else if (z == 2) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 1;
			} else if (random == 3) {
				direction = 3;
			} else if (random == 1) {
				direction = 3;
			}
		}

		else if (z == 3) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 2;
			} else if (random == 3) {
				direction = 4;
			} else if (random == 1) {
				direction = 4;
			}
		}

		else if (z == 4) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 3;
			} else if (random == 3) {
				direction = 5;
			} else if (random == 1) {
				direction = 5;
			}
		}

		else if (z == 5) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 4;
			} else if (random == 3) {
				direction = 6;
			} else if (random == 1) {
				direction = 6;
			}
		}

		else if (z == 6) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 7;
			} else if (random == 3) {
				direction = 5;
			} else if (random == 1) {
				direction = 5;
			}
		}

		if (z == 7) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 6;
			} else if (random == 3) {
				direction = 8;
			} else if (random == 1) {
				direction = 8;
			}
		}

		else if (z == 8) {
			stay = false;
			if (random == 2 || random == 4) {
				direction = 1;
			} else if (random == 3) {
				direction = 7;
			} else if (random == 1) {
				direction = 7;
			}
		}
	}

	public void shoot() {
		if (!cool) {
			AudioPlayer.getSound(getWeapon().getSound()).play();
			int random = (accuracy * -1) + (int) (Math.random() * ((accuracy - (accuracy * -1)) + (accuracy * -1)));
			Bullet b = new Bullet(getX(), getY(), Game.getInstance().play.player.getX() + random,
					Game.getInstance().play.player.getY() + random, bullets);
			bullets.add(b);
			cool = true;
		}
	}

	public void cooldown() {
		if (cool) {
			counter += 1;
			if (counter == getWeapon().getSpeed()) {
				cool = false;
				counter = 0;
			}
		}
	}

	public ArrayList<Bullet> getList() {
		return bullets;
	}

	public void setIsNew(boolean b){
		isNew = b;
	}
	
	public boolean isNew(){
		return isNew;
	}
	
	@SuppressWarnings("static-access")
	public void act() {
			if (active == true) {
				Game.getInstance().play.enemyGroup.removeUnit(this);
			}
			if (getAngle() >= 360) {
				setAngle(0);
			}

			if (getAngle() <= -45) {
				setAngle(315);
			}

			if (direction == 9) {
				setAction("standing");
				if (getAngle() <= 0) {
					setAngle(getAngle() + 360);
				}
				if (getAngle() > 360) {
					setAngle(getAngle() - 360);
				}
				if (Math.abs(getAngle()
						- getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) <= 2
						&& 0 <= getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					shoot();
				}

				if (Math.abs(getAngle()
						- (360 + getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY()))) <= 2
						&& 0 > getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					shoot();
				}

				if (getAngle() > getBetween(getX(), getY(),
						Game.getInstance().play.player.getX(),
						Game.getInstance().play.player.getY())
						&& 0 <= getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					setAngle(getAngle() - 1);
				} else if (getAngle() < getBetween(getX(), getY(),
						Game.getInstance().play.player.getX(),
						Game.getInstance().play.player.getY())
						&& 0 <= getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					setAngle(getAngle() + 1);
				} else if (getAngle() < 360 + getBetween(getX(), getY(),
						Game.getInstance().play.player.getX(),
						Game.getInstance().play.player.getY())
						&& 0 > getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > getBetween(getX(), getY(),
						Game.getInstance().play.player.getX(),
						Game.getInstance().play.player.getY())
						&& 0 > getBetween(getX(), getY(),
								Game.getInstance().play.player.getX(),
								Game.getInstance().play.player.getY())) {
					setAngle(getAngle() - 1);
				}
			} else if (stay == false)
				setAction("moving");
			else
				setAction("standing");
			if (direction == 1) {
				if (stay == false) {
					setLoc(getX(), getY() + 1);
				}
				if (getAngle() < 90) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 90) {
					setAngle(getAngle() - 1);
				}
			} else if (direction == 2) {
				if (stay == false) {
					setLoc(getX() + 1, getY() + 1);
				}
				if (getAngle() < 45) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 45) {
					setAngle(getAngle() - 1);
				}
			} else if (direction == 3) {
				if (stay == false) {
					setLoc(getX() + 1, getY());
				}
				if (getAngle() < 0) {
					setAngle(getAngle() + 1);
				} else if (getAngle() <= 180 && getAngle() > 0
						&& getAngle() != 0) {
					setAngle(getAngle() - 1);
				} else if (getAngle() > 180 && getAngle() < 360
						&& getAngle() != 0) {
					setAngle(getAngle() + 1);
				}
			} else if (direction == 4) {
				if (stay == false) {
					setLoc(getX() + 1, getY() - 1);
				}
				if (getAngle() < 315 && getAngle() > 180) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 315) {
					setAngle(getAngle() - 1);
				} else if (getAngle() < 315 && getAngle() < 180) {
					setAngle(getAngle() - 1);
				}
			} else if (direction == 5) {
				if (stay == false) {
					setLoc(getX(), getY() - 1);
				}
				if (getAngle() < 270) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 270) {
					setAngle(getAngle() - 1);
				}
			} else if (direction == 6) {
				if (stay == false) {
					setLoc(getX() - 1, getY() - 1);
				}
				if (getAngle() < 225) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 225) {
					setAngle(getAngle() - 1);
				}
			}

			else if (direction == 7) {
				if (stay == false) {
					setLoc(getX() - 1, getY());
				}
				if (getAngle() < 180) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 180) {
					setAngle(getAngle() - 1);
				}
			} else if (direction == 8) {
				if (stay == false) {
					setLoc(getX() - 1, getY() + 1);
				}
				if (getAngle() < 135) {
					setAngle(getAngle() + 1);
				} else if (getAngle() > 135) {
					setAngle(getAngle() - 1);
				}
			}
			if (getY() > Game.getInstance().frame.getHeight() && (!Game.getInstance().play.map.isMoving()) ) {
				direction = 5;
			} else if (getY() == 0 && (!Game.getInstance().play.map.isMoving())) {
				direction = 1;
			}
			if (getX() > Game.getInstance().frame.getWidth() && (!Game.getInstance().play.map.isMoving())) {
				direction = 7;
			} else if (getX() < 0 && (!Game.getInstance().play.map.isMoving())) {
				direction = 3;
			}
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void setDirection(int s) {
		direction = s;
	}

	public void death() {
		Game.getInstance().play.enemyGroup.removeUnit(this);
		setAction("");
		direction = 0;
		Explosion boom = new Explosion(getX(), getY(), 0, "", 0, 0, getWeapon());
		Game.getInstance().play.explosions.addUnit(boom);
	}

	public void hit(int x, int y) {
		Spark s = new Spark(x, y, 0, "", 0, 0, getWeapon());
		Game.getInstance().play.sparks.addUnit(s);
	}

	public void draw(Graphics g) {
		// Graphics
		if (getAction() == "standing") {
			actor = standing;
		}
		if (getAction() == "moving") {
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
					bul.rotate(Math.toRadians(getBetween(aBullet.getX(), aBullet.getY(), aBullet.getMX(),
							aBullet.getMY())));
					bul.scale(0.5, 0.5);
					bul.translate(-getWeapon().getActor().getWidth() / 2, -getWeapon().getActor().getHeight() / 2);
					g2d.drawImage(getWeapon().getActor(), bul, null);
				}
			}
		} catch (ConcurrentModificationException e) {
		}
		AffineTransform at = new AffineTransform();
		at.translate(getX(), getY());
		at.rotate(Math.toRadians(getAngle()));
		at.scale(0.5, 0.5);
		at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
		g2d.drawImage(actor, at, null);
	}

	public void remove() {
		active = true;
		setAction("");
		direction = 0;
	}

}
