package com.thinkbox.sf.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.model.Asteroid;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class TileMap {
	private int currentTileX;
	private int currentTileY;
	private boolean move;
	private int drawX;
	private int drawY;
	private int xDiff;
	private int yDiff;
	private int direction;
	private BufferedImage[][] tiles = new BufferedImage[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT];
	private boolean locked;

	public boolean getLock(){
		return locked;
	}
	
	public void setLock(boolean b){
		locked = b;
	}
	
	public TileMap(int cx, int cy) {
		currentTileX = cx;
		currentTileY = cy;
	}

	public int getX() {
		return currentTileX;
	}

	public int getY() {
		return currentTileY;
	}

	public BufferedImage getTile(int x, int y) {
		return tiles[x][y];
	}
	public void destroy(){
		try {
			for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
				if(((Enemy)aUnit).isNew() == false){
					((Enemy) aUnit).remove();
				}
				else
					((Enemy) aUnit).setIsNew(false);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		}
		
		try {
			for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
				if(((Asteroid)aUnit).isNew() == false){
					((Asteroid) aUnit).remove();
				}
				else
					((Asteroid) aUnit).setIsNew(false);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		}
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH, Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
		return dimg;
	}
	
	public boolean isMoving(){
		return move;
	}
	
	public void setTile(int x, int y, BufferedImage b) {
		tiles[x][y] = b;
	}

	@SuppressWarnings("static-access")
	public void draw(Graphics g) {
	try{
		resize(tiles[currentTileX][currentTileY], Game.getInstance().frame.getWidth(), Game.getInstance().frame.getHeight(), g, drawX, drawY);
		if(move == true){
			if (direction == 1) {
				resize(tiles[currentTileX][currentTileY + 1], Game.getInstance().frame.getWidth(), Game.getInstance().frame.getHeight(), g, drawX, drawY
						- Game.getInstance().frame.getHeight());
			}
			else if (direction == 2) {
				resize(tiles[currentTileX][currentTileY - 1], Game.getInstance().frame.getWidth(), Game.getInstance().frame.getHeight(), g, drawX, drawY
						+ Game.getInstance().frame.getHeight());
			}
			else if (direction == 3) {
				resize(tiles[currentTileX + 1][currentTileY], Game.getInstance().frame.getWidth(), Game.getInstance().frame.getHeight(), g, drawX + Game.getInstance().frame.getWidth(), drawY);
			}
			else if (direction == 4) {
				resize(tiles[currentTileX - 1][currentTileY], Game.getInstance().frame.getWidth(), Game.getInstance().frame.getHeight(), g, drawX - Game.getInstance().frame.getWidth(), drawY);
			}
		}
		resize(Images.mergeUp, tiles[currentTileX][currentTileY].getWidth(), 150, g, 0, drawY - 75 );
		resize(Images.mergeUp, tiles[currentTileX][currentTileY].getWidth(), 150, g, 0, drawY + Game.getInstance().frame.getHeight() - 75 );
		resize(Images.mergeRight, 150, tiles[currentTileX][currentTileY].getHeight(), g, drawX - 75, 0 );
		resize(Images.mergeRight, 150, tiles[currentTileX][currentTileY].getHeight(), g, drawX + Game.getInstance().frame.getWidth()  - 75, 0 );
	}catch(IndexOutOfBoundsException e){
	}
	}
	
	public void spawnTile(int x, int y){
		if (x == 0 && y == 0) {
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(500 + xDiff, 600 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(300 + xDiff, 100 + yDiff, true));
			//locked = true;
			//Game.getInstance().play.timers.add(new Timer(10000, "Tiles"));
		}
		
		if (x == 0 && y == 1) {
			Game.getInstance().play.enemyGroup.addUnit(GameConstants.redShip.create(600 + xDiff, 600 + yDiff, true));
			Game.getInstance().play.enemyGroup.addUnit(GameConstants.redShip.create(300 + xDiff, 300 + yDiff, true));
		}
		
		if (x == 0 && y == 2) {
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(600 + xDiff, 600 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(300 + xDiff, 100 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(500 + xDiff, 600 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(300 + xDiff, 200 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(400 + xDiff, 300 + yDiff, true));
			Game.getInstance().play.asteriods.addUnit(GameConstants.asteroid1.create(421 + xDiff, 500 + yDiff, true));
		}
	}
	
	public void setCurrent(int x, int y) {
		destroy();
		currentTileX = x;
		currentTileY = y;
		Game.getInstance().play.effects.setCreate(true);
	}
	
	public void transitionRight(){
		Game.getInstance().play.effects.setCreate(false);
		xDiff = Game.getInstance().getWidth();
		yDiff = 0;
		direction = 3;
		spawnTile(getX() + 1, getY());
		while(drawX + Game.frame.getWidth() != 0){
			move = true;
			drawX -= 1;
			if(Game.getInstance().play.player.isAlive())
				Game.getInstance().play.player.setLoc(Game.getInstance().play.player.getX() - 1, Game.getInstance().play.player.getY());
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					aUnit.setLoc(aUnit.getX() - 1, aUnit.getY());
				}
			} catch (ConcurrentModificationException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					aUnit.setLoc(aUnit.getX() - 1, aUnit.getY());
				}
			} catch (ConcurrentModificationException e) {
			}
			try {
				Thread.sleep(GameConstants.MOVEMENT_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		setCurrent(getX() + 1, getY());
		move = false;
		drawX = 0;
		drawY = 0;
		xDiff = 0;
		yDiff = 0;
	}
	
	public void transitionLeft(){
		Game.getInstance().play.effects.setCreate(false);
		xDiff = -Game.getInstance().getWidth();
		yDiff = 0;
		direction = 4;
		spawnTile(getX() - 1, getY());
		while(drawX - Game.frame.getWidth() != 0){
			move = true;
			drawX += 1;
			if(Game.getInstance().play.player.isAlive())
				Game.getInstance().play.player.setLoc(Game.getInstance().play.player.getX() + 1, Game.getInstance().play.player.getY());
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					aUnit.setLoc(aUnit.getX() + 1, aUnit.getY());
				}
			} catch (ConcurrentModificationException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					aUnit.setLoc(aUnit.getX() + 1, aUnit.getY());
				}
			} catch (ConcurrentModificationException e) {
			}
			try {
				Thread.sleep(GameConstants.MOVEMENT_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		setCurrent(getX() - 1, getY());
		move = false;
		drawX = 0;
		drawY = 0;
		xDiff = 0;
		yDiff = 0;
	}
	
	public void transitionUp(){
		Game.getInstance().play.effects.setCreate(false);
		xDiff = 0;
		yDiff = -Game.getInstance().getHeight();
		direction = 1;
		spawnTile(getX(), getY() + 1);
		while(drawY < Game.frame.getHeight()){
			move = true;
			drawY += 1;
			if(Game.getInstance().play.player.isAlive())
				Game.getInstance().play.player.setLoc(Game.getInstance().play.player.getX(), Game.getInstance().play.player.getY() + 1);
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					aUnit.setLoc(aUnit.getX(), aUnit.getY() + 1);
				}
			} catch (ConcurrentModificationException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					aUnit.setLoc(aUnit.getX(), aUnit.getY() + 1);
				}
			} catch (ConcurrentModificationException e) {
			}
			try {
				Thread.sleep(GameConstants.MOVEMENT_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		setCurrent(getX(), getY() + 1);
		move = false;
		drawX = 0;
		drawY = 0;
		xDiff = 0;
		yDiff = 0;
	}
	
	public int getDrawY(){
		return drawY;
	}
	
	public int getDrawX(){
		return drawX;
	}
	
	public void transitionDown(){
		Game.getInstance().play.effects.setCreate(false);
		setDiff(0, Game.getInstance().getHeight());
		setDerection(2);
		spawnTile(getX(), getY() - 1);
		while(getDrawY() + Game.frame.getHeight() != 0){
			setMove(true);
			setDraw(0, getDrawY() - 1);
			if(Game.getInstance().play.player.isAlive())
				Game.getInstance().play.player.setLoc(Game.getInstance().play.player.getX(), Game.getInstance().play.player.getY() - 1);
			try {
				for (Unit aUnit : Game.getInstance().play.enemyGroup.getActive()) {
					aUnit.setLoc(aUnit.getX(), aUnit.getY() - 1);
				}
			} catch (ConcurrentModificationException e) {
			}
			
			try {
				for (Unit aUnit : Game.getInstance().play.asteriods.getActive()) {
					aUnit.setLoc(aUnit.getX(), aUnit.getY() - 1);
				}
			} catch (ConcurrentModificationException e) {
			}
			try {
				Thread.sleep(GameConstants.MOVEMENT_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		setCurrent(getX(), getY() - 1);
		setMove(false);
		setDraw(0, 0);
		setDiff(0, 0);
	}
	
	public void setDiff(int x, int y){
		xDiff = x;
		yDiff = y;
	}
	
	public void setDerection(int x){
		direction = x;
	}
	
	public void setDraw(int x, int y){
		drawY = y;
		drawX = x;
	}
	
	public void setMove(boolean b){
		move = b;
	}
	
	@SuppressWarnings("static-access")
	public void drawWordsUp(Graphics g, boolean b) {

		Font tempFont = new Font("Arial", Font.BOLD, 45);
		g.setFont(tempFont);
		g.setColor(Color.WHITE);
		if(b == true)
		g.drawString("Sector " + currentTileX + ", " + (currentTileY + 1),
				(int) (Game.getInstance().frame.getWidth() / 2.4), 50);
		else
			g.drawString("Sector Locked!",
					(int) (Game.getInstance().frame.getWidth() / 2.4), 50);
	}

	@SuppressWarnings("static-access")
	public void drawWordsDown(Graphics g, boolean b) {
		if (currentTileY - 1 >= 0) {
			Graphics2D g2D = (Graphics2D) g;
			Font tempFont = new Font("Arial", Font.BOLD, 45);
			g.setFont(tempFont);
			g.setColor(Color.WHITE);
			AffineTransform fontAT = new AffineTransform();
			Font theFont = g2D.getFont();
			fontAT.rotate(Math.toRadians(180));
			Font theDerivedFont = theFont.deriveFont(fontAT);
			g2D.setFont(theDerivedFont);
			if(b == true)
				g2D.drawString("Sector " + currentTileX + ", " + (currentTileY - 1),
						(int) (Game.getInstance().frame.getWidth() / 2) + 100, Game.getInstance().frame.getHeight() - 50);
			else
				g2D.drawString("Sector Locked!",
						(int) (Game.getInstance().frame.getWidth() / 2) + 100, Game.getInstance().frame.getHeight() - 50);
			g2D.setFont(theFont);
		}
	}

	@SuppressWarnings("static-access")
	public void drawWordsRight(Graphics g, boolean b) {
		if (currentTileX + 1 < GameConstants.MAP_WIDTH) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double height = screenSize.getHeight();
			Graphics2D g2D = (Graphics2D) g;
			Font tempFont = new Font("Arial", Font.BOLD, 45);
			g.setFont(tempFont);
			g.setColor(Color.WHITE);
			AffineTransform fontAT = new AffineTransform();
			Font theFont = g2D.getFont();
			fontAT.rotate(Math.toRadians(90));
			Font theDerivedFont = theFont.deriveFont(fontAT);
			g2D.setFont(theDerivedFont);
			if(b == true)
				g2D.drawString("Sector " + (currentTileX + 1) + ", " + currentTileY,
						Game.getInstance().frame.getWidth() - 75, (int) (height / 2) - 115);
			else
				g2D.drawString("Sector Locked!" ,
						Game.getInstance().frame.getWidth() - 75, (int) (height / 2) - 115);
			g2D.setFont(theFont);
		}
	}

	public void drawWordsLeft(Graphics g, boolean b) {
		if ((currentTileX - 1 >= 0)) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double height = screenSize.getHeight();
			Graphics2D g2D = (Graphics2D) g;
			Font tempFont = new Font("Arial", Font.BOLD, 45);
			g.setFont(tempFont);
			g.setColor(Color.WHITE);
			AffineTransform fontAT = new AffineTransform();
			Font theFont = g2D.getFont();
			fontAT.rotate(Math.toRadians(270));
			Font theDerivedFont = theFont.deriveFont(fontAT);
			g2D.setFont(theDerivedFont);
			if(b == true)
				g2D.drawString("Sector " + (currentTileX - 1) + ", " + currentTileY, 75, (int) (height / 2) + 115);
			else
				g2D.drawString("Sector Locked!", 75, (int) (height / 2) + 115);
			g2D.setFont(theFont);
		}
	}
}
