package com.thinkbox.sf.control;

public class Timer {
	private int time;
	private int current;
	private boolean conclude;
	private String purpose;
	
	public Timer(int i, String f){
		time = i;
		purpose = f;
	}
	
	public String getPurpose(){
		return purpose;
	}
	
	public boolean getDone(){
		return conclude;
	}
	
	public void tick(){
		current += 1;
		if(current == time){
			conclude = true;
		}
	}
}
