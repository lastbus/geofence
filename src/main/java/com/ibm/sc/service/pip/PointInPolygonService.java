package com.ibm.sc.service.pip;

import com.ibm.sc.bean.Polygon;
import com.infomatiq.jsi.Point;

public interface PointInPolygonService {
	/**
	 * check whether point in polygon including slides
	 * @param point
	 * @param polygon
	 * @return
	 */
	public boolean pointInPolygon(Point point, Polygon polygon)  throws Exception;

}
