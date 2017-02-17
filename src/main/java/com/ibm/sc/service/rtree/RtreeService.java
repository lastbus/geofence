package com.ibm.sc.service.rtree;

import java.util.List;

import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;

public interface RtreeService {
	/**
	 * add Rectangle to r-tree and re-index
	 * @param rectangle
	 * @param id
	 * @return success or fail
	 */
	public void addRectangleIntoRtree(Rectangle rectangle, Integer id) throws Exception;
	
	/**
	 * get Rectangle list that cover the point using r-tree 
	 * @param point
	 * @return
	 */
	public List<Integer> getRectangleListByPoint(Point point)  throws Exception;

}
