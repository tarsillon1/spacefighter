package com.thinkbox.sf.control;

import java.awt.Color;
import java.awt.Graphics;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;

public class Renderer {
	public void act() {
		switch (Game.state) {
		case GAME:
			break;
		case MENU:
			break;

		case PAUSE:
			Game.getInstance().running = false;
			break;
		default:
			break;
		}
	}

	public void renderBackround(Graphics g) {
		switch (Game.state) {
		case GAME:
			Game.getInstance().play.render(g);
			break;
		case MENU:
			Game.frame.setSize(GameConstants.WIDTH, GameConstants.HEIGHT);
			Game.frame.setLocationRelativeTo(null);
			Game.getInstance().play.player.revive();
			Game.getInstance().menu.render(g);
			break;
		case PAUSE:
			Game.getInstance().running = false;
			break;
		default:
			g.setColor(Color.RED);
			g.drawString("UNKNOWN GAMESTATE", 150, 150);
			break;
		}
		Game.getInstance().options.render(g);
	}

	public void renderForeground(Graphics g) {
	}
}
