package com.thinkbox.sf.utils;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.views.Load;

public class AudioPlayer 
{
	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	
	public static void addSound(String key, String path)
	{
		Load.setMessage("Loading audio from " + GameConstants.SOUND_LOCATION);
		try {
			soundMap.put(key, new Sound(path));
		} catch (SlickException e) {
		}
	}
	
	public static Sound getSound(String key)
	{
		return soundMap.get(key);
	}
}
