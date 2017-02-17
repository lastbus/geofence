package com.ibm.sc.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibm.sc.bean.Polygon;
import com.ibm.sc.service.geofence.GeoFenceService;
import com.ibm.sc.service.geofence.impl.GeoFenceServiceImpl;
import com.infomatiq.jsi.Point;

/**
 * client = [121.427475,31.238729]
 * 
 * 
 * @author susaijie
 *
 */
public class Test {

	static final int NUM = 1000;

	public static void main(String[] args) throws Exception{

		GeoFenceService geoFenceTool = new GeoFenceServiceImpl();

		/**
		 * init,
		 */
		HashMap<Integer, Polygon> polygonMap = new HashMap<Integer, Polygon>();
		
		for (int i = 0; i < NUM; i++) {
			List<Point> corners = new ArrayList<Point>();
			corners.add(new Point(121.398067f + i / 10000f, 31.260504f + i / 10000f));
			corners.add(new Point(121.405277f + i / 10000f, 31.26696f + i / 10000f));
			corners.add(new Point(121.411285f + i / 10000f, 31.270628f + i / 10000f));
			corners.add(new Point(121.435146f + i / 10000f, 31.268427f + i / 10000f));
			corners.add(new Point(121.448364f + i / 10000f, 31.262265f + i / 10000f));
			corners.add(new Point(121.452312f + i / 10000f, 31.255661f + i / 10000f));
			corners.add(new Point(121.455231f + i / 10000f, 31.248617f + i / 10000f));
			corners.add(new Point(121.448021f + i / 10000f, 31.239371f + i / 10000f));
			corners.add(new Point(121.453857f + i / 10000f, 31.227335f + i / 10000f));
			corners.add(new Point(121.418495f + i / 10000f, 31.225133f + i / 10000f));
			corners.add(new Point(121.404591f + i / 10000f, 31.22895f + i / 10000f));
			corners.add(new Point(121.384678f + i / 10000f, 31.240839f + i / 10000f));
			corners.add(new Point(121.374722f + i / 10000f, 31.250525f + i / 10000f));
			Polygon polygon = new Polygon(corners);
			polygonMap.put((10001 + i), polygon);
		}
		/**
		 * add polygon
		 */
		Long startTime1 = System.currentTimeMillis();
		for (Integer id : polygonMap.keySet()) {
			try {
				geoFenceTool.addPolygon(id, polygonMap.get(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long endTime1 = System.currentTimeMillis();
		System.out.println("addPolygon time taken:   " + (endTime1 - startTime1) + " ms");
		/**
		 * test point
		 */
		Point curPoint = new Point(121.428877f, 31.250654f);
		
		Long startTime2 = System.currentTimeMillis();
		/**
		 * get MBRList by rtree
		 */
		List<Integer> tempIds = geoFenceTool.getMBRList(curPoint);
		/**
		 * get PolygonMap By ids
		 */
		HashMap<Integer, Polygon> matchMap = new HashMap<Integer, Polygon>();
		for (Integer id : tempIds) {
			matchMap.put(id, polygonMap.get(id));
		}
		/**
		 * get ids by PIP
		 */
		List<Integer> resultIds = geoFenceTool.getPolygonList(curPoint, matchMap);		
		Long endTime2 = System.currentTimeMillis();
		System.out.println("rtree getPolygonList: " + (endTime2 - startTime2) +  " ms");
		System.out.println("match count:  " +  resultIds.size());
/*		for (Integer curId : resultIds) {
			System.out.println(curId);
		}*/
		/**
		 * get ids by pure PIP
		 */
		Long startTime4 = System.currentTimeMillis();
		List<Integer> resultIds2 = geoFenceTool.getPolygonList(curPoint, polygonMap);
		Long endTime4 = System.currentTimeMillis();
		System.out.println("pure getPolygonList:  " +  (endTime4 - startTime4) + " ms");
		System.out.println("match count:  "  +  resultIds2.size());
	/*	for (Integer curId : resultIds2) {
			System.out.println(curId);
		}*/

	}

}
