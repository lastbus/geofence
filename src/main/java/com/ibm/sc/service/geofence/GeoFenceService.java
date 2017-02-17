package com.ibm.sc.service.geofence;

import java.util.HashMap;
import java.util.List;

import com.ibm.sc.bean.Polygon;
import com.infomatiq.jsi.Point;

public interface GeoFenceService {
	/**
	 * 新增多边形并初始化，获取MBR，MBR加入空间索引
	 * 
	 * @param id
	 *            多边形唯一标识
	 * @param polygon
	 *            多边形
	 */
	public void addPolygon(Integer id, Polygon polygon) throws Exception;

	/**
	 * 通过空间索引获取包含点的MBR ID列表
	 * 
	 * @param point
	 * @return
	 */
	public List<Integer> getMBRList(Point point) throws Exception;

	/**
	 * 通过暴力破解获取包含点的多边形 ID列表
	 * 
	 * @param point
	 * @param polygonMap
	 *            ID与多边形的MAP
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getPolygonList(Point point, HashMap<Integer, Polygon> polygonMap) throws Exception;

}
