package com.thinkbox.sf.control;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.model.Fog_Particle;

public class GenerateFog {
	private boolean create;
	
	public GenerateFog(){
	}
	
	public void setCreate(boolean b){
		create = b;
	}
	
	public void generate(){
		if(create == true){
			int randomValueX = 1 + (int) (Math.random() * ((Game.frame.getWidth() - 1) + 1));
			int randomValueY = 1 + (int) (Math.random() * ((Game.frame.getHeight() - 1) + 1));
			new Fog_Particle(Images.fog, randomValueX, randomValueY);
			randomValueX = 1 + (int) (Math.random() * ((Game.frame.getWidth() - 1) + 1));
			randomValueY = 1 + (int) (Math.random() * ((Game.frame.getHeight() - 1) + 1));
			new Fog_Particle(Images.fog, randomValueX, randomValueY);
		}
	}
}
