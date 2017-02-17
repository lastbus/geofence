package com.ibm.sc.service.geofence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibm.sc.bean.Polygon;
import com.ibm.sc.service.geofence.GeoFenceService;
import com.ibm.sc.service.mbr.MBRService;
import com.ibm.sc.service.mbr.impl.MBRServiceImpl;
import com.ibm.sc.service.pip.PointInPolygonService;
import com.ibm.sc.service.pip.impl.PointInPolygonServiceImpl;
import com.ibm.sc.service.rtree.RtreeService;
import com.ibm.sc.service.rtree.impl.RtreeServiceImpl;
import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;

public class GeoFenceServiceImpl implements GeoFenceService{
	
	MBRService mbrService = new MBRServiceImpl();
	
	RtreeService rtreeService = new RtreeServiceImpl();
	
	PointInPolygonService pipService = new PointInPolygonServiceImpl();
	
	public void addPolygon(Integer id, Polygon polygon) throws Exception{
		if(polygon == null || id == null){
			throw new Exception("argument is null");
		}
		if(polygon.getCorners() == null || polygon.getCorners().size() < 3){
			throw new Exception("polygon is illegal");
		}
		Rectangle rectangle = mbrService.getMBROfPolygon(polygon);
		rtreeService.addRectangleIntoRtree(rectangle, id);
	}

	public List<Integer> getMBRList(Point point) throws Exception{
		List<Integer> ids = rtreeService.getRectangleListByPoint(point);
		return ids;
	}

	public List<Integer> getPolygonList(Point point, HashMap<Integer, Polygon> polygonMap) throws Exception{
		List<Integer> ids = new ArrayList<Integer>();
		if(polygonMap != null && !polygonMap.isEmpty()){
			for(Integer id : polygonMap.keySet()){
				if(pipService.pointInPolygon(point, polygonMap.get(id))){
					ids.add(id);
				}
			}
		}
		return ids;
	}

}
