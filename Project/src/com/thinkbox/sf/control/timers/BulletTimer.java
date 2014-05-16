package com.thinkbox.sf.control.timers;

import java.awt.Rectangle;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Bullet;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class BulletTimer extends Thread {
	public BulletTimer() {
	}

	public void run() {
		while (true) {
				try {
					for (Bullet aBullet : Game.getInstance().play.player
							.getList()) {
						Rectangle rec = new Rectangle(
								aBullet.getX(),
								aBullet.getY(),
								(int) (Game.getInstance().play.player
										.getWeapon().getActor().getWidth() / GameConstants.HIT_SCALE),
								(int) (Game.getInstance().play.player
										.getWeapon().getActor().getHeight() / GameConstants.HIT_SCALE));
						for (Unit aUnit2 : Game.getInstance().play.asteriods
								.getActive()) {
							Rectangle recE = new Rectangle(aUnit2.getX(),
									aUnit2.getY(),
									(int) (((Asteroid) aUnit2).getActor()
											.getWidth()),
									(int) (((Asteroid) aUnit2).getActor()
											.getHeight()));
							if (rec.intersects(recE)) {
								aUnit2.setHealth(aUnit2.getHealth()
										- Game.getInstance().play.player
												.getWeapon().getDamage());
								aUnit2.hit(aUnit2.getX(), aUnit2.getY());
								aBullet.setActive(false);
							}
						}
					}
				} catch (ConcurrentModificationException e) {
				} catch (NullPointerException e) {
				}

			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup
						.getActive()) {
					try {
						for (Bullet aBullet : ((Enemy) aUnit).getList()) {
							if (!aBullet.isActive()) {
								((Enemy) aUnit).getList().remove(aBullet);
							}
						}
					} catch (ConcurrentModificationException e) {
					}
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			}

			try {
				for (Bullet aBullet : Game.getInstance().play.player.getList()) {
					if (!aBullet.isActive()) {
						Game.getInstance().play.player.getList()
								.remove(aBullet);
					}
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			}


			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup
						.getActive()) {
					try {
						try {
							for (Bullet aBullet : ((Enemy) aUnit).getList()) {
								if (aBullet.isActive()) {
									aBullet.move();
								}
							}
						} catch (NullPointerException e) {
						}
					} catch (ConcurrentModificationException e) {
					}
				}
			} catch (ConcurrentModificationException e) {
			}

			try {
				for (Bullet aBullet : Game.getInstance().play.player.getList()) {
					if (aBullet.isActive()) {
						aBullet.move();
					}
				}
			} catch (ConcurrentModificationException e) {
			}
				try {
					for (Bullet aBullet : Game.getInstance().play.player
							.getList()) {
						if (aBullet.isActive()) {
							Rectangle rect = new Rectangle(
									aBullet.getX(),
									aBullet.getY(),
									(int) (Game.getInstance().play.player
											.getWeapon().getActor().getWidth() / GameConstants.HIT_SCALE),
									(int) (Game.getInstance().play.player
											.getWeapon().getActor().getHeight() / GameConstants.HIT_SCALE));
							try {
								for (Unit aUnit : Game.getInstance().play.enemyGroup
										.getActive()) {
									Rectangle rect2 = new Rectangle(
											aUnit.getX(),
											aUnit.getY(),
											(int) (((Enemy) aUnit).getActor()
													.getWidth() / GameConstants.HIT_SCALE),
											(int) (((Enemy) aUnit).getActor()
													.getHeight() / GameConstants.HIT_SCALE));
									if (rect.intersects(rect2)) {
										aBullet.setActive(false);
										((Enemy) aUnit).hit(aBullet.getX(),
												aBullet.getY());
										aUnit.setHealth(aUnit.getHealth()
												- Game.getInstance().play.player
														.getWeapon()
														.getDamage());
									}

									try {
										for (Bullet eBullet : ((Enemy) aUnit)
												.getList()) {
											try {
												if (eBullet.isActive()) {
													Rectangle rect3 = new Rectangle(
															eBullet.getX(),
															eBullet.getY(),
															(int) (((Enemy) aUnit)
																	.getWeapon()
																	.getActor()
																	.getWidth() / GameConstants.HIT_SCALE),
															(int) (((Enemy) aUnit)
																	.getWeapon()
																	.getActor()
																	.getHeight() / GameConstants.HIT_SCALE));
													if (rect.intersects(rect3)) {
														eBullet.setActive(false);
														aBullet.setActive(false);
													}
												}
											} catch (NullPointerException e) {

											}
										}
									} catch (ConcurrentModificationException e) {
									}
								}
							} catch (ConcurrentModificationException e) {
							} catch (NullPointerException e) {
							}

						}

					}
				} catch (ConcurrentModificationException e) {
				}

				try {
					for (Unit aUnit : Game.getInstance().play.enemyGroup
							.getActive()) {
						try {
							for (Bullet eBullet : ((Enemy) aUnit).getList()) {
								if (eBullet.isActive()) {
									Rectangle rect3 = new Rectangle(
											eBullet.getX(),
											eBullet.getY(),
											(int) (((Enemy) aUnit).getWeapon()
													.getActor().getWidth() / GameConstants.HIT_SCALE),
											(int) (((Enemy) aUnit).getWeapon()
													.getActor().getHeight() / GameConstants.HIT_SCALE));
									Rectangle rect4 = new Rectangle(
											Game.getInstance().play.player
													.getX(),
											Game.getInstance().play.player
													.getY(),
											(int) (Game.getInstance().play.player
													.getActor().getWidth() / GameConstants.HIT_SCALE),
											(int) (Game.getInstance().play.player
													.getActor().getHeight() / GameConstants.HIT_SCALE));
									if (rect3.intersects(rect4)) {
										eBullet.setActive(false);
										Game.getInstance().play.player.hit(
												eBullet.getX(), eBullet.getY());
										aUnit.setHealth(Game.getInstance().play.player
												.getHealth()
												- aUnit.getWeapon().getDamage());
									}
								}
							}
						} catch (ConcurrentModificationException e) {
						}
					}
				} catch (ConcurrentModificationException e) {
				} catch (NullPointerException e) {
				} catch (NoSuchElementException e) {
				}

			try {
				sleep(GameConstants.BULLET_INTERVAL);
			} catch (InterruptedException e) {
			}

		}
	}
}
