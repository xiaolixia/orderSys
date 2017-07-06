/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.service.admin;

import java.util.ArrayList;

import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

/**
 * <p>
 * Title: DishesService
 * </p>
 * <p>
 * Description: 锟斤拷品锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class DishesService {


	
	/**
	 * 锟斤拷莶锟狡稩D值删锟斤拷锟狡凤拷锟较拷姆锟斤拷锟�
	 * 
	 * @param dishesId
	 *            要删锟斤拷牟锟狡稩d
	 * */
	public void deleteDishesById(Integer dishesId) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷删锟斤拷锟斤拷锟�
		helper.executePreparedUpdate("delete from dishesinfo where dishesId=?",
				new Object[] { dishesId });
		
	}

	/**
	 * 锟斤拷硬锟狡凤拷姆锟斤拷锟�
	 * 
	 * @param info
	 *            锟斤拷要锟斤拷拥牟锟狡凤拷锟较�
	 * */
	public void addDishes(DishesInfo info) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷锟斤拷硬锟斤拷锟�
		helper.executePreparedUpdate(
				"insert into dishesinfo(dishesName,dishesDiscript,dishesTxt,dishesImg,recommend,dishesPrice) values(?,?,?,?,?,?)",
				new Object[] { info.getDishesName(), info.getDishesDiscript(),
						info.getDishesTxt(), info.getDishesImg(),
						new Integer(info.getRecommend()),
						new Float(info.getDishesPrice()) });

	}

	/**
	 * 锟斤拷锟絛ishesId锟斤拷取锟斤拷品锟斤拷细锟斤拷息锟侥凤拷锟斤拷
	 * 
	 * @param dishesId
	 *            要锟斤拷取锟斤拷息锟斤拷锟截讹拷锟斤拷品Id
	 * @return 锟斤拷锟截革拷id锟侥诧拷品锟斤拷细锟斤拷息
	 * */
	public DishesInfo getDishesById(Integer dishesId) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询锟斤拷id锟侥诧拷品锟斤拷细锟斤拷息
		ArrayList<DishesInfo> list = helper.preparedQueryForList(
				"select * from dishesinfo where dishesId=?",
				new Object[] { dishesId }, DishesInfo.class);
		// 锟斤拷锟截诧拷询锟斤拷锟�
		return list.get(0);
	}

	/**
	 * 锟睫改诧拷品锟斤拷息锟侥凤拷锟斤拷
	 * 
	 * @param Info
	 *            要锟睫改的诧拷品锟斤拷息锟斤拷锟斤拷锟斤拷dishesId为锟睫革拷锟斤拷锟捷ｏ拷锟斤拷锟斤拷锟斤拷息为锟睫改碉拷目锟斤拷值
	 * */
	public void modifyDishes(DishesInfo info) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷锟睫改诧拷锟斤拷
		helper.executePreparedUpdate(
				"update dishesinfo set dishesName=?,dishesDiscript=?,dishesTxt=?,dishesImg=?,recommend=?,dishesPrice=? where dishesId=?",
				new Object[] { info.getDishesName(), info.getDishesDiscript(),
						info.getDishesTxt(), info.getDishesImg(),
						new Integer(info.getRecommend()),
						new Float(info.getDishesPrice()),
						new Integer(info.getDishesId()) });

	}

	/**
	 * 锟斤拷取头4锟斤拷锟狡硷拷锟斤拷品锟斤拷锟斤拷息
	 * 
	 * @return 头4锟斤拷锟狡硷拷锟斤拷品锟叫憋拷
	 * */
	public ArrayList<DishesInfo> getSomeRecommendDishes(int page) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<DishesInfo> list = helper.preparedForPageList(
				"select * from dishesinfo  order by dishesId",
				new Object[] {}, page, 4, DishesInfo.class);

		// 锟斤拷锟截斤拷锟�
		return list;

	}
	
	public ArrayList<DishesInfo> getSomeRecommendDishes(int page,int offset) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<DishesInfo> list = helper.preparedForPageList(
				"select * from dishesinfo  order by dishesId",
				new Object[] {}, page, offset, DishesInfo.class);

		// 锟斤拷锟截斤拷锟�
		return list;

	}
	
	
	public int getFinalPageoffset(int offset) {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<DishesInfo> list = helper.queryForList("select * from dishesinfo",DishesInfo.class);
		
		int t=list.size();
		int page = (t/offset)+(t%offset==0?0:1);

		return page;

	}
	
	
	
	public int getFinalPage() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<DishesInfo> list = helper.queryForList("select * from dishesinfo",DishesInfo.class);
		
		int t=list.size();
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}
	
	public ArrayList<DishesInfo> getTop4RecommendDishes() {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<DishesInfo> list = helper.preparedForPageList(
				"select * from dishesinfo where recommend=1 order by dishesId",
				new Object[] {}, 1, 4, DishesInfo.class);

		// 锟斤拷锟截斤拷锟�
		return list;

	}
	



}
