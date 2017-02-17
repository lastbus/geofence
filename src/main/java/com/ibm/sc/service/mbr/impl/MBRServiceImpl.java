package com.ibm.sc.service.mbr.impl;

import java.util.List;

import com.ibm.sc.bean.Polygon;
import com.ibm.sc.service.mbr.MBRService;
import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;

public class MBRServiceImpl implements MBRService{

	public Rectangle getMBROfPolygon(Polygon polygon) throws Exception{
		if(polygon == null){
			throw new Exception("argument is null");
		}
		if(polygon.getCorners() == null || polygon.getCorners().size() < 3){
			throw new Exception("polygon is illegal");
		}
		List<Point> polygonCorners = polygon.getCorners();
		float minX = polygonCorners.get(0).x;
		float minY = polygonCorners.get(0).y;
		float maxX = polygonCorners.get(0).x;
		float maxY = polygonCorners.get(0).y;
		for(int i=1; i< polygonCorners.size(); i++){
			if(polygonCorners.get(i).x > maxX){
				maxX = polygonCorners.get(i).x ;
			}
			if(polygonCorners.get(i).x < minX){
				minX = polygonCorners.get(i).x ;
			}
			if(polygonCorners.get(i).y > maxY){
				maxY = polygonCorners.get(i).y ;
			}
			if(polygonCorners.get(i).y < minY){
				minY = polygonCorners.get(i).y ;
			}
		}
		return new Rectangle(minX,minY,maxX,maxY);
	}

}
