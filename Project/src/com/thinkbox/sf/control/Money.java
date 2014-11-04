package com.thinkbox.sf.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.thinkbox.sf.constants.Images;

public class Money {
	private int current;
	public Money(int p){
		current = p;
	}
	
	public int getMoney(){
		return current;
	}
	
	public void addMoney(int i){
		current += i;
	}
	
	public void draw(Graphics g){
		g.drawImage(Images.money, 50, 15, null);
		g.setColor(new Color(207,181,59));
		g.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 35));
		g.drawString(current + "$", 177, 48);
	}
}
