package com.xorinc.personify.data;

import org.bukkit.Material;

public class BlockColorTuple {

		private Material mat;
		private int data, red, green, blue;
		
		public BlockColorTuple(Material mat, int data, int red, int green, int blue){
			this.mat = mat;
			this.data = data;
			this.red =  red;
			this.green = green;
			this.blue = blue;
		}

		public Material getMat() {
			return mat;
		}

		public byte getData() {
			return (byte) data;
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
		
}
