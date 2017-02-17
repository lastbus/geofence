package com.ibm.sc.bean;

import java.util.List;

import com.infomatiq.jsi.Point;

public class Polygon{
	/**
	 * corners in polygon
	 */
	private List<Point> corners;
	
	public Polygon(){
		
	}
	
	public Polygon(List<Point> corners){
		this.corners = corners;
	}

	public List<Point> getCorners() {
		return corners;
	}

	public void setCorners(List<Point> corners) {
		this.corners = corners;
	}
}
