package com.thinkbox.sf.constants;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.model.AsteroidType;
import com.thinkbox.sf.model.EnemyType;

public class GameConstants {
	public static final int CENTER_X = Game.WIDTH / 2;
	public static final int CENTER_Y = Game.HEIGHT / 2;
	public static final String RESOURCE_LOCATION = "./resources/";
	public static final String SPRITE_LOCATION = RESOURCE_LOCATION + "sprites/";
	public static final String SOUND_LOCATION = RESOURCE_LOCATION + "sounds/";
	public static final int MOVEMENT_INTERVAL = 10;
	public static final int BULLET_INTERVAL = 2;
	public static final int GAMELOOP_INTERVAL = 10;
	public static final int RESET = 1000;
	public static final int BULLET_MULTIPLIER = 1;
	public static final int MAX_BULLETS = 50;
	public static final int BULLET_DESTROY = 700;
	public static final int COOLDOWN_INTERVAL = 1;
	public static final int AI_INTERVAL = 1750;
	public static final int MAX_BOUND = 2000;
	public static final int CHANCE = 8;
	public static final double HIT_SCALE = 4;
	public static final int EXPLODE_INTERVAL = 225;
	public static final int SPARK_INTERVAL = 150;
	public static final int MAP_WIDTH = 3;
	public static final int MAP_HEIGHT = 3;
	public static final int COLIDE_INTERVAL = 1000;
	public static final int CRASH_SCALE = 3;
	public static final int END_WAIT = 5000;
	public static final int WIDTH = 640;
	public static final int STARTX = 0;
	public static final int STARTY = 0;
	public static final int CRASH_INTERVAL = 100;
	public static final int MAP_INTERVAL = 10;
	public static final int ASTEROID_INTERVAL = 10;
	public static final int DUST_INTERVAL = 90;
	public static final int FOG_INTERVAL = 10;
	public static final int PARTICLE_INTERVAL = 90;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final String TITLE = "Test Game";
	public static final EnemyType redShip = new EnemyType(0, "standing", 100, 100, Images.ship2Stand, Images.ship2Walk,
			Game.getInstance().play.enemyWeapon1, 45);
	public static final AsteroidType asteroid1 = new AsteroidType(100, "", 100, 100, null, Images.asteroid1);
	public static final int MUSIC_INTERVAL = 188 * 1000;
}
