package com.popcode.dungeongame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.popcode.dungeongame.DungeonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.width = 800;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new DungeonGame(), config);
	}
}
