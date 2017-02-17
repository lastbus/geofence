package com.ibm.sc.service.rtree.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibm.sc.service.rtree.RtreeService;
import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;

import gnu.trove.TIntProcedure;

public class RtreeServiceImpl implements RtreeService {

	private static SpatialIndex si = null;

	/**
	 * get current r-tree from cache
	 * 
	 * @return
	 */
	private static SpatialIndex getCurrentRtreeFromCache() {
		if (si == null) {
			si = new RTree();
			si.init(null);
		}
		return si;
	}

	public void addRectangleIntoRtree(Rectangle rectangle, Integer id) throws Exception{
		if(rectangle == null || id == null){
			throw new Exception("argument is null");
		}
		SpatialIndex si = getCurrentRtreeFromCache();
		if(si == null){
			throw new Exception("rtree is null");
		}
		si.add(rectangle, id);
	}

	public List<Integer> getRectangleListByPoint(Point point) throws Exception{
		if(point == null){
			throw new Exception("argument is null");
		}
		SpatialIndex si = getCurrentRtreeFromCache();
		if(si == null){
			throw new Exception("rtree is null");
		}
		SaveToListProcedure myProc = new SaveToListProcedure();
		si.nearest(point, myProc, 0f);
		return myProc.getIds();
	}

	private class SaveToListProcedure implements TIntProcedure {
		private List<Integer> ids = new ArrayList<Integer>();

		public boolean execute(int id) {
			ids.add(id);
			return true;
		};

		private List<Integer> getIds() {
			return ids;
		}
	};

}
