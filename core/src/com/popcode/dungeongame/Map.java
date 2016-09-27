package com.popcode.dungeongame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	
	SpriteBatch b = DungeonGame.batch;
	
	int roomSpacing = 2;
	
	static int map[][];
	Room room[];
	int minDimension; 
	int maxDimension;
	int xPos, yPos, roomLength, roomHeight;
	boolean roomPossible = true, runLoop;
	public static int mapSpacing = 10; 
	int roomsDrawn = 0;
	int roomAttempts = 0;
	
	public Map(int mapWidth, int mapHeight, int minDim, int maxDim, int roomNumber){
		
		map = new int[mapWidth][mapHeight];
		room = new Room[roomNumber];
		minDimension = minDim;
		maxDimension = maxDim;
		
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map.length; y++){
				map[x][y] = 0;
			}
		}
		
		for(int i = 0; i < roomNumber; i++){
			createMap();
		}
		
	}
	
	public static int getMapspacing(){
		return mapSpacing;
	}
	
	public static int getHeight(){
		return map[1].length;
	}
	
	public static int getLength() {
		return map.length;
	}
	
	public void render(){
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map.length; y++){
				b.draw(Textures.texture[map[x][y]], x*mapSpacing, y*mapSpacing, mapSpacing, mapSpacing);
			}
		}
	}
	
	public void createMap(){
		roomAttempts++;
		log(roomAttempts);
		
		xPos = (int)(Math.random()*(map.length + 1));
		yPos = (int)(Math.random()*(map.length + 1));
		roomLength = (int)(Math.random()*(maxDimension - minDimension + 1)) + minDimension;
		roomHeight = (int)(Math.random()*(maxDimension - minDimension+ 1)) + minDimension;
		
		boolean canDraw = checkRoom();
		
		if(canDraw){
			drawRoom();
		} else if(roomAttempts > 100){
			roomAttempts = 0;
		} else {
			createMap();
		} 
		
	}
	
	public boolean checkRoom(){
		runLoop = true;
		while(runLoop){
			if(map.length > (roomLength + xPos + (roomSpacing)) && map[xPos].length > (roomHeight + yPos + (roomSpacing)) && 0 < (yPos - (roomSpacing)) && 0 < (xPos - (roomSpacing))){
				for(int x = (xPos-roomSpacing); x < (roomLength + xPos + roomSpacing); x++){
					for(int y = (yPos-roomSpacing); y < (roomHeight + yPos + roomSpacing); y++){
						if(map[x][y] != 0 && runLoop){
							roomPossible = false;
							runLoop = false;
						} else if(runLoop){
							roomPossible = true;
						}
						log("(" + x + ", " + y + ") " + map[x][y] + " room possible? " + roomPossible);
					}
				}
				runLoop = false;
			} else {
				roomPossible = false;
				runLoop = false;
			}
			
		}
		return roomPossible;
	}
	
	public void drawRoom(){
		room[roomsDrawn] = new Room(xPos, yPos, roomLength, roomHeight);
		roomsDrawn++;
		log("Room drawns " + roomsDrawn + " at " + xPos + ", " + yPos + " length is " + roomLength + " height is " + roomHeight);
		for(int x = (xPos); x < (xPos + roomLength); x++){
			for(int y = (yPos); y < (yPos + roomHeight); y++){
				map[x][y] = 1;
			}
		}
	}
	
	
	public void createCorridor(){
		
	}
	
	public void log(Object o){
		//System.out.println(o);
	}
	
}
