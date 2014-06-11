package com.xorinc.personify.data;

public class PositionDataTuple {
	
	private int xl, xh, yl, yh, dim1, dim2, sx, sy, sz;
	private boolean flip = false;
	
	
	public PositionDataTuple(int xl, int xh, int yl, int yh, int dim1, int dim2, int sx, int sy, int sz){
		this.xl = xl;
		this.xh = xh;
		this.yl = yl;
		this.yh = yh;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
	}
	
	public PositionDataTuple(int xl, int xh, int yl, int yh, int dim1, int dim2, int sx, int sy, int sz, boolean flip){
		this.xl = xl;
		this.xh = xh;
		this.yl = yl;
		this.yh = yh;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
		this.flip = flip;
	}


	public int getXl() {
		return xl;
	}


	public int getXh() {
		return xh;
	}


	public int getYl() {
		return yl;
	}
	
	
	public int getYh() {
		return yh;
	}

	public int getDim1() {
		return dim1;
	}


	public int getDim2() {
		return dim2;
	}


	public int getSx() {
		return sx;
	}


	public int getSy() {
		return sy;
	}


	public int getSz() {
		return sz;
	}
	
	public boolean isFlipped(){
		return flip;
	}

}
