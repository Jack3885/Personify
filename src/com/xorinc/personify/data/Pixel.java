package com.xorinc.personify.data;

public class Pixel {
	
	private int red, green, blue, alpha;
	
	public Pixel(int RGB){ 
		this.red = (RGB >> 16) & 0xff;
		this.green = (RGB >> 8) & 0xff;
		this.blue = (RGB) & 0xff;
		this.alpha = (RGB >> 24) & 0xff;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
	
	public boolean isTransparent(){
		return alpha < 128;
	}
	
	public String toString(){
		return red + " " + green + " " + blue + " " + alpha;
	}
}
