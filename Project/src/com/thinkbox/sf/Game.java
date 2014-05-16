package com.thinkbox.sf;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.control.Colisions;
import com.thinkbox.sf.control.Renderer;
import com.thinkbox.sf.control.inputs.KeyInput;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.control.timers.AsteroidTimer;
import com.thinkbox.sf.control.timers.BulletTimer;
import com.thinkbox.sf.control.timers.CooldownTimer;
import com.thinkbox.sf.control.timers.CrashTimer;
import com.thinkbox.sf.control.timers.DustTimer;
import com.thinkbox.sf.control.timers.EnemyTimer;
import com.thinkbox.sf.control.timers.ExplosionTimer;
import com.thinkbox.sf.control.timers.HealthTimer;
import com.thinkbox.sf.control.timers.MovementTimer;
import com.thinkbox.sf.control.timers.MusicTimer;
import com.thinkbox.sf.control.timers.SparkTimer;
import com.thinkbox.sf.control.timers.TileMapTimer;
import com.thinkbox.sf.utils.ResourceLoader;
import com.thinkbox.sf.views.Load;
import com.thinkbox.sf.views.Menu;
import com.thinkbox.sf.views.Options;
import com.thinkbox.sf.views.Play;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -8381964091870710794L;

	public static JFrame frame = new JFrame();
	private static Game game = new Game();
	public static GameState state = GameState.LOADING;
	public boolean running = false;
	private Thread thread;
	private Renderer gfx;
	public Menu menu;
	public Play play;
	public Options options;
	public boolean frankwashere = false; 
	
	private int time;
	private int counter = 0;
	
	public void tick() {
		if(Game.state == GameState.LOADING){
			time --;
			if(time <= 0){
				load();
				time = 40;
			}
		}
	}

	public static Game getInstance() {
		return game;
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(6, 0, 40));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		g.fillRect(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		// /////////////////////////////////////////////////////////////////
		if (Game.state == GameState.LOADING) {
			Load.render(g);
		} else {
			gfx.renderBackround(g);
			gfx.renderForeground(g);
		}
		g.dispose();
		bs.show();
		frankwashere = true;
	}

	public void init() {
		ResourceLoader.preLoadImages();
	}
	
	public void load(){
		switch(counter){
			case 0:
				ResourceLoader.loadImages();
				counter ++;
				Load.loadMore();
				return;
			case 1:
				ResourceLoader.loadSounds();
				counter ++;
				Load.loadMore();
				return;
			case 2:
				Load.setMessage("Loading Menu");
				menu = new Menu();
				options = new Options();
				counter ++;
				Load.loadMore();
				return;
			case 3:
				Load.setMessage("Loading Game");
				play = new Play();
				counter ++;
				Load.loadMore();
				return;
			case 4:
				Load.setMessage("Loading Timers");
				gfx = new Renderer();
				new MovementTimer(gfx).start();
				new BulletTimer().start();
				new CooldownTimer().start();
				new EnemyTimer().start();
				new HealthTimer().start();
				new ExplosionTimer().start();
				new SparkTimer().start();
				new Colisions().start();
				new CrashTimer().start();
				new AsteroidTimer().start();
				new DustTimer().start();
				new TileMapTimer().start();
				new MusicTimer(GameConstants.MUSIC_INTERVAL).start();
				counter ++;
				Load.loadMore();
				return;
			case 5:
				Load.setMessage("Loading Mouse Inputs");
				MouseInput mouse = new MouseInput();
				this.addMouseListener(mouse);
				this.addMouseMotionListener(mouse);
				this.addKeyListener(new KeyInput());
				counter ++;
				Load.loadMore();
				return;
			case 6:
				counter ++;
				Load.loadMore();
				state = GameState.MENU;
				Game.getInstance().menu.playMusic();
				return;
		}
	}

	@SuppressWarnings("static-access")
	public void run() {
		init();
		while (running) {
			tick();
			render();
			try {
				new Thread().sleep(GameConstants.GAMELOOP_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		stop();
	}

	public static void main(String args[]) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cursor = toolkit.getImage(GameConstants.RESOURCE_LOCATION + "cursor.png");
		Image icon = Toolkit.getDefaultToolkit().getImage(GameConstants.RESOURCE_LOCATION + "icon.png");

		frame.add(game);
		frame.setTitle(GameConstants.TITLE);
		frame.setSize(GameConstants.WIDTH, GameConstants.HEIGHT);
		frame.setIconImage(icon);
		frame.setCursor(toolkit.createCustomCursor(cursor, new Point(frame.getX(), frame.getY()), "cursor"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		game.start();
	}

	private synchronized void start() {
		if (running)
			return;
		else
			running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		else
			running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
		System.exit(1);
	}
}


