package com.popcode.dungeongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Textures {
	
	TextureAtlas atlas;
	public static AtlasRegion floor, nothing;
	public static Sprite player;
	public static TextureRegion[] texture = new TextureRegion[50];
	public static Sprite[] sprites = new Sprite[50];
	int tPos = 0;
	int sPos = 0;
	
	public Textures(){
		atlas = new TextureAtlas(Gdx.files.internal("Dungeon Crawler Pack.pack"));
		addTexture("BackGround");
		addTexture("meh");
		addSprite("player");	
	}
	
	public void addTexture(String fileName){
		texture[tPos] = atlas.findRegion(fileName);
		tPos++;
	}
	
	public void addSprite(String fileName){
		sprites[sPos] = atlas.createSprite(fileName);
		sPos++;
	}
	
	public static TextureRegion getTextures(int pos){
		return texture[pos];
	}
	
	public static Sprite getSprites(int pos){
		return sprites[pos];
	}
	
}
