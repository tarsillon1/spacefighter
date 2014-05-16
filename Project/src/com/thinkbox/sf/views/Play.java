package com.thinkbox.sf.views;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.ActiveUnits;
import com.thinkbox.sf.control.GenerateFog;
import com.thinkbox.sf.control.TileMap;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Dust_Explosion;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Explosion;
import com.thinkbox.sf.model.Fog_Particle;
import com.thinkbox.sf.model.Player;
import com.thinkbox.sf.model.Spark;
import com.thinkbox.sf.model.Unit;
import com.thinkbox.sf.model.Weapon;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Play {
	public Weapon playerWeapon1 = new Weapon(10, Images.bullet1, 200, "Double Laser Gun", Audio.shoot);
	public Weapon playerWeapon2 = new Weapon(5, Images.bullet2, 200, "Single Laser Gun", Audio.shoot);
	public Weapon enemyWeapon1 = new Weapon(5, Images.bullet3, 300, "Quad Shot Gun", Audio.shoot1);
	public Player player = new Player(400, 160, 0, "standing", 100, 100, Images.ship1Stand, Images.ship1Walk,
			playerWeapon1);
	public ActiveUnits enemyGroup = new ActiveUnits();
	public ActiveUnits explosions = new ActiveUnits();
	public ActiveUnits dustE = new ActiveUnits();
	public ActiveUnits sparks = new ActiveUnits();
	public ActiveUnits asteriods = new ActiveUnits();
	public ActiveUnits moved = new ActiveUnits();
	public ArrayList<Fog_Particle> particles = new ArrayList<Fog_Particle>();
	public GenerateFog effects = new GenerateFog();

	public TileMap map = new TileMap(0, 0);

	public Play() {
		map.setTile(0, 0, Images.tile1);
		map.setTile(0, 1, Images.tile2);
		map.setTile(0, 2, Images.tile3);
		map.setTile(1, 0, Images.tile8);
		map.setTile(1, 1, Images.tile5);
		map.setTile(1, 2, Images.tile1);
		map.setTile(2, 0, Images.tile7);
		map.setTile(2, 1, Images.tile8);
		map.setTile(2, 2, Images.tile9);
	}

	@SuppressWarnings("static-access")
	public void render(Graphics g) {
		map.draw(g);
		
		//Effects/////////////////////////////////////////////////////////////////////////////
		try {
			for (Fog_Particle aParticle : particles) {
				aParticle.draw(g);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
		try {
			for (Fog_Particle aParticle : Game.getInstance().play.particles) {
				aParticle.act();
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch(NullPointerException e){
		}
		if(Game.getInstance().options.getOpen())
			Game.getInstance().play.effects.generate();
		///////////////////////////////////////////////////////////////////////////////////////
		
		Rectangle rec = new Rectangle(0, 0, Game.getInstance().frame.getWidth(), 50);
		Rectangle rec2 = new Rectangle(0, Game.getInstance().frame.getHeight() - 50,
				Game.getInstance().frame.getWidth(), 100);
		Rectangle rec3 = new Rectangle(Game.getInstance().frame.getWidth() - 100, 0, 100,
				Game.getInstance().frame.getHeight());
		Rectangle rec4 = new Rectangle(0, 0, 100, Game.getInstance().frame.getHeight());
		if (MouseInput.MOUSE.intersects(rec)) {
			map.drawWordsUp(g);
		}
		if (MouseInput.MOUSE.intersects(rec2)) {
			map.drawWordsDown(g);
		}
		if (MouseInput.MOUSE.intersects(rec3)) {
			map.drawWordsRight(g);
		}
		if (MouseInput.MOUSE.intersects(rec4)) {
			map.drawWordsLeft(g);
		}
		Game.frame.setLocation(0, 0);
		Game.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		player.draw(g);
		try {
			for (Unit aUnit : enemyGroup.getActive()) {
				((Enemy) aUnit).draw(g);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		}
		
		try {
			for (Unit aUnit : asteriods.getActive()) {
				((Asteroid) aUnit).draw(g);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		}

		try {
			for (Unit aUnit : sparks.getActive()) {
				((Spark) aUnit).draw(g);
			}
		} catch (ConcurrentModificationException e) {
		}

		try {
			for (Unit aUnit : explosions.getActive()) {
				((Explosion) aUnit).draw(g);
			}
		} catch (ConcurrentModificationException e) {
		}
		
		try {
			for (Unit aUnit : dustE.getActive()) {
				((Dust_Explosion) aUnit).draw(g);
			}
		} catch (ConcurrentModificationException e) {
		}
	}

	public void playMusic() {
		AudioPlayer.getSound(Audio.gameMusic).play();
	}
}
