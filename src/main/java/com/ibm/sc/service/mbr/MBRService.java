package com.ibm.sc.service.mbr;

import com.ibm.sc.bean.Polygon;
import com.infomatiq.jsi.Rectangle;

public interface MBRService {
	
	/**
	 * get MBR of polygon 
	 * @param polygon
	 * @return
	 */
	public Rectangle getMBROfPolygon(Polygon polygon) throws Exception;

}
