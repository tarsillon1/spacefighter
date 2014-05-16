package com.thinkbox.sf.control;

import java.util.ArrayList;
import com.thinkbox.sf.model.Unit;

public class ActiveUnits {
	private ArrayList<Unit> units = new ArrayList<Unit>();

	public ActiveUnits() {
	}

	public void addUnit(Unit unit) {
		try{
			units.add(unit);
		}catch(IndexOutOfBoundsException e){
		}
	}

	public ArrayList<Unit> getActive() {
		return units;
	}

	public void removeUnit(Unit unit) {
		try{
			units.remove(unit);
		}catch(IndexOutOfBoundsException e){	
		}
	}

	public boolean hasUnit(Unit aUnit){ 
		return units.contains(aUnit);
	}
}
