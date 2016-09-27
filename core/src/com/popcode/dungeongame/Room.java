package com.popcode.dungeongame;

public class Room {

	int xPos, yPos, roomLength, roomHeight;
	
	public Room (int x, int y, int length, int height){
		xPos = x;
		yPos = y;
		roomLength = length;
		roomHeight = height;
	}	
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public int getLength(){
		return roomLength;
	}
	
	public int getHeight(){
		return roomHeight;
	}
	
}
