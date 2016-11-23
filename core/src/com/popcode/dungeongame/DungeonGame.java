package com.popcode.dungeongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DungeonGame extends Game {
	public static SpriteBatch batch;
	OrthographicCamera cam;

	int playerX, playerY;
	int movespeed = 4;
	int roomSpacing = 4;

	static Map map;
	Textures t;
	Player p;
	int mapSpacing = Map.getMapspacing();
	
	public void create () {
		batch = new SpriteBatch();
		map = new Map(); 
		t = new Textures();
		p = new Player();
	}

	public void render () {
		p.camUpdate();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		map.render();
		p.drawPlayer();
		batch.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
	
}
