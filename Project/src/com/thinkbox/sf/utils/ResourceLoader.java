package com.thinkbox.sf.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.views.Load;

;

public class ResourceLoader {
	private static BufferedImageLoader imageLoader = new BufferedImageLoader();
	
	public static void preLoadImages() {
		try {
			Images.logo = imageLoader.loadImage("logo.png");
			Images.loading = imageLoader.loadImage("loading.png");
		} catch (IOException e) {
		}
	}
	
	public static void loadImages() {
		try {
			Images.check = imageLoader.loadImage("check.png");
			Images.noCheck = imageLoader.loadImage("noCheck.png");
			Images.fog = imageLoader.loadImage("smoke.png");
			Images.tile3 = imageLoader.loadImage("backround2.png");
			Images.tile2 = imageLoader.loadImage("tile2.png");
			Images.tile1 = imageLoader.loadImage("space.png");
			Images.tile4 = imageLoader.loadImage("tile4.png");
			Images.tile5 = imageLoader.loadImage("tile5.png");
			Images.tile6 = imageLoader.loadImage("tile6.png");
			Images.tile7 = imageLoader.loadImage("tile7.png");
			Images.tile8 = imageLoader.loadImage("tile8.png");
			Images.tile9 = imageLoader.loadImage("tile9.png");
			Images.asteroid1 = imageLoader.loadImage("asteroid.png");
			Images.explosion1 = imageLoader.loadImage("explosion1.png");
			Images.explosion2 = imageLoader.loadImage("explosion2.png");
			Images.explosion3 = imageLoader.loadImage("explosion3.png");
			Images.dust1 = imageLoader.loadImage("dust1.png");
			Images.dust2 = imageLoader.loadImage("dust2.png");
			Images.dust3 = imageLoader.loadImage("dust3.png");
			Images.dust4 = imageLoader.loadImage("dust4.png");
			Images.dust5 = imageLoader.loadImage("dust5.png");
			Images.dust6 = imageLoader.loadImage("dust6.png");
			Images.dust7 = imageLoader.loadImage("dust7.png");
			Images.bullet1 = imageLoader.loadImage("bullet.png");
			Images.bullet2 = imageLoader.loadImage("bullet1.png");
			Images.bullet3 = imageLoader.loadImage("bullet3.png");
			Images.spark1 = imageLoader.loadImage("sparks1.png");
			Images.spark2 = imageLoader.loadImage("sparks2.png");
			Images.spark3 = imageLoader.loadImage("sparks3.png");
			Images.ship1Stand = imageLoader.loadImage("ship1Stand.png");
			Images.ship1Walk = imageLoader.loadImage("ship1Walk.png");
			Images.ship2Stand = imageLoader.loadImage("ship2Stand.png");
			Images.ship2Walk = imageLoader.loadImage("ship2Walk.png");
			Images.backround = imageLoader.loadImage("backround.png");
			Images.title = imageLoader.loadImage("title.png");
			Images.play = imageLoader.loadImage("play.png");
			Images.options = imageLoader.loadImage("options.png");
			Images.exit = imageLoader.loadImage("exit.png");
			Images.over = imageLoader.loadImage("gameover.png");
		} catch (IOException e) {
		}
	}
	
	public static void loadSounds()
	{
		AudioPlayer.addSound(Audio.menuMusic, GameConstants.SOUND_LOCATION + "ES_Posthumus_Unstoppable.ogg");
		AudioPlayer.addSound(Audio.buttonClick, GameConstants.SOUND_LOCATION + "Laser.ogg");
		AudioPlayer.addSound(Audio.buttonHover, GameConstants.SOUND_LOCATION + "hover.ogg");
		AudioPlayer.addSound(Audio.shoot, GameConstants.SOUND_LOCATION + "shoot.ogg");
		AudioPlayer.addSound(Audio.shoot1, GameConstants.SOUND_LOCATION + "shoot1.ogg");
		AudioPlayer.addSound(Audio.gameMusic, GameConstants.SOUND_LOCATION + "gameMusic1.ogg");
		AudioPlayer.addSound(Audio.explode, GameConstants.SOUND_LOCATION + "explode.ogg");
		AudioPlayer.addSound(Audio.dustExplode, GameConstants.SOUND_LOCATION + "dirtExplosion.ogg");
		AudioPlayer.addSound(Audio.moveSound, GameConstants.SOUND_LOCATION + "moving.ogg");

	}
}
