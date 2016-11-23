package com.popcode.dungeongame;

import java.util.Arrays;

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
	static int mapSpacing = 10; 
	int roomsDrawn = 0;
	int roomAttempts = 0;
	int roomCenter[], prevCenter[];
	
	public Map(){
		this(64, 64, 7, 12, 10);
	}
	
	public Map(int mapWidth, int mapHeight, int minDim, int maxDim, int roomNumber){
		
		this.map = new int[mapWidth][mapHeight];
		this.room = new Room[roomNumber];
		this.minDimension = minDim;
		this.maxDimension = maxDim;
		roomCenter = new int[2];
		prevCenter = new int[2];
		
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map.length; y++){
				this.map[x][y] = 0;
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
		
		this.xPos = (int)(Math.random()*(map.length + 1));
		this.yPos = (int)(Math.random()*(map.length + 1));
		this.roomLength = (int)(Math.random()*(maxDimension - minDimension + 1)) + minDimension;
		this.roomHeight = (int)(Math.random()*(maxDimension - minDimension+ 1)) + minDimension;
		
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
		for(int x = (xPos); x < (xPos + roomLength); x++){
			for(int y = (yPos); y < (yPos + roomHeight); y++){
				map[x][y] = 1;
			}
		}
		roomCenter[0] = xPos + (roomLength/2);
		roomCenter[1] = yPos + (roomHeight/2);
		createCorridor();
		prevCenter[0] = roomCenter[0];
		prevCenter[1] = roomCenter[1];
	}
	
	public void createCorridor(){
		log("corridor");
		log("(" + (prevCenter[0]) + ", " + (prevCenter[1]) + ")" + "(" + roomCenter[0] + ", " + roomCenter[1] + ")");
		if(prevCenter[0] != 0 && prevCenter[1] != 0){
			//log("if run");
			//Draw X
			if(prevCenter[0] > roomCenter[0]){
				log("x prev");
				for(int x = roomCenter[0]; x < prevCenter[0]; x++){
					map[x][roomCenter[1]] = 1;
				}
			}else if(prevCenter[0] < roomCenter[0]){
				log("x curr");
				for(int x = prevCenter[0]; x < roomCenter[0]; x++){
					map[x][prevCenter[1]] = 1;
				}
			}
			
			//Draw Y
			if(prevCenter[1] > roomCenter[1]){
				log("y prev");
				for(int y = roomCenter[1]; y < prevCenter[1]; y++){
					map[roomCenter[0]][y] = 1;
				}
			} else if(prevCenter[1] < roomCenter[1]){
				log("y curr");
				for(int y = prevCenter[1]; y < roomCenter[1]; y++){
					map[prevCenter[1]][y] = 1;
				}
			}
			
		} else {
			log("first room");
		}
	}
	
	public void log(Object o){
		System.out.println(o);
	}
	
}
