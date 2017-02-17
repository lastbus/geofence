package com.ibm.sc.service.pip.impl;

import java.util.List;

import com.ibm.sc.bean.Polygon;
import com.ibm.sc.service.pip.PointInPolygonService;
import com.infomatiq.jsi.Point;

public class PointInPolygonServiceImpl implements PointInPolygonService {
	/**
	 * 点向y轴正方向发射一条射线,计算射线和多边形的交点数。交点数为奇数则在内，否则在外.
	 * 对于点位于多边形边界，进行单独判定
	 */
	public boolean pointInPolygon(Point point, Polygon polygon) throws Exception{
		if(polygon == null || point == null){
			throw new Exception("argument is null");
		}
		if(polygon.getCorners() == null || polygon.getCorners().size() < 3){
			throw new Exception("polygon is illegal");
		}
		boolean oddNodes = false;
		List<Point> polyCorners = polygon.getCorners();
		int polyCornersSize = polyCorners.size();
		int i, j = polyCornersSize - 1;
		
		/**
		 * judge point is on polygon slide
		 */
		for (i = 0; i < polyCornersSize; i++) {
			if (isPointOnSegment(point, polyCorners.get(i), polyCorners.get(j))) {
				oddNodes = true;
				break;
			}
			j = i;
		}
		if(oddNodes){
			return oddNodes;
		}
		
		/**
		 * judge point is in polygon
		 */
		i = polyCornersSize - 1;
		j = polyCornersSize - 1;
		for (i = 0; i < polyCornersSize; i++) {
			if ((polyCorners.get(i).y < point.y && polyCorners.get(j).y >= point.y
					|| polyCorners.get(j).y < point.y && polyCorners.get(i).y >= point.y)
					&& (polyCorners.get(i).x <= point.x || polyCorners.get(j).x <= point.x)) {
				oddNodes ^= (polyCorners.get(i).x
						+ (point.y - polyCorners.get(i).y) / (polyCorners.get(j).y - polyCorners.get(i).y)
								* (polyCorners.get(j).x - polyCorners.get(i).x) < point.x);
			}
			j = i;
		}
		return oddNodes;
	}

	private boolean isPointOnSegment(Point point, Point segmentPoint1, Point segmentPoint2) {
		if(onSegment(point, segmentPoint1, segmentPoint2) && direction(point, segmentPoint1, segmentPoint2) == 0){
			return true;
		}else{
			return false;
		}
	}

	private float direction(Point point, Point segmentPoint1, Point segmentPoint2) {
		return (segmentPoint1.x - point.x) * (segmentPoint2.y - point.y)
				- (segmentPoint2.x - point.x) * (segmentPoint1.y - point.y);
	}

	private boolean onSegment(Point point,Point segmentPoint1, Point segmentPoint2) {
		float maxX = segmentPoint1.x > segmentPoint2.x ? segmentPoint1.x : segmentPoint2.x;
		float minX = segmentPoint1.x < segmentPoint2.x ? segmentPoint1.x : segmentPoint2.x;
		float maxY = segmentPoint1.y > segmentPoint2.y ? segmentPoint1.y : segmentPoint2.y;
		float minY = segmentPoint1.y < segmentPoint2.y ? segmentPoint1.y : segmentPoint2.y;
		if (point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY){
			return true;
		}else{
			return false;
		}	
	}
}
