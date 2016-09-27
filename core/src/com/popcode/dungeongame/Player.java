package com.popcode.dungeongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	OrthographicCamera cam;
	
	int playerX, playerY;
	int movespeed = 2;
	int mapSpacing;
	SpriteBatch b = DungeonGame.batch;
	
	public Player(){		
		mapSpacing = Map.getMapspacing();
		playerX = 32*mapSpacing;
		playerY = 32*mapSpacing;
		
		cam = new OrthographicCamera(800, 800);
		cam.setToOrtho(false, 800, 800);
		cam.position.set(playerX, playerY, 0);
		cam.update();
	}
	
	public void camUpdate(){
		playerMovement();
		cam.position.x = playerX;
		cam.position.y = playerY;
		b.setProjectionMatrix(cam.combined);
		cam.update();
	}
	
	public void drawPlayer(){
		b.draw(Textures.getSprites(0), playerX, playerY, mapSpacing, mapSpacing);
	}
	
	public void playerMovement(){
		if(Gdx.input.isKeyPressed(Keys.W) && playerY < (Map.getHeight() * mapSpacing) - mapSpacing){
			playerY += movespeed;
		} 
		if(Gdx.input.isKeyPressed(Keys.S) && playerY > 0){
			playerY -= movespeed;
		}
		if(Gdx.input.isKeyPressed(Keys.A) && playerX > 0){
			playerX -= movespeed;
		}
		if(Gdx.input.isKeyPressed(Keys.D) && playerX < (Map.getLength() * mapSpacing) - mapSpacing){
			playerX += movespeed;
		}
	
	}
	
}
