/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.service.kitchen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.OrderInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;

/**
 * <p>
 * Title: OrderService
 * </p>
 * <p>
 * Description: 菜品订单管理服务对象
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
public class OrderService {


	
	/**
	 * 根据菜品ID值删除菜品信息的方法
	 * 
	 * @param dishesId
	 *            要删除的菜品Id
	 * */
	public void deleteDishesById(Integer dishesId) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执行删除操作
		helper.executePreparedUpdate("delete from dishesinfo where dishesId=?",
				new Object[] { dishesId });
	}

	/**
	 * 添加菜品的方法
	 * 
	 * @param info
	 *            需要添加的菜品信息
	 * */
	public void addDishes(DishesInfo info) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执行添加操作
		helper.executePreparedUpdate(
				"insert into dishesinfo(dishesName,dishesDiscript,dishesTxt,dishesImg,recommend,dishesPrice) values(?,?,?,?,?,?)",
				new Object[] { info.getDishesName(), info.getDishesDiscript(),
						info.getDishesTxt(), info.getDishesImg(),
						new Integer(info.getRecommend()),
						new Float(info.getDishesPrice()) });

	}
	
	/**
	 * 修改菜品信息的方法
	 * 
	 * @param Info
	 *            要修改的菜品信息，其中dishesId为修改依据，其余信息为修改的目标值
	 * */
	public void modifyOrders(int id) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy=MM-dd HH:mm:ss");
		// 执行修改操作
			helper.executePreparedUpdate(
				"update orderinfo set orderState=1,orderEndDate=? where orderId=?",
				new Object[] {df.format(new Date()),id});

	}
	
	
	
	
	

	/**
	 * 根据dishesId获取菜品详细信息的方法
	 * 
	 * @param dishesId
	 *            要获取信息的特定菜品Id
	 * @return 返回该id的菜品详细信息
	 * */
	/*	public OrderInfo getOrdersById(Integer dishesId) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 查询给定id的菜品详细信息
		ArrayList<OrderInfo> list = helper.preparedQueryForList(
				"select * from dishesinfo where dishesId=?",
				new Object[] { dishesId }, OrderInfo.class);
		// 返回查询结果
		return list.get(0);
	
	}
*///有点问题，需要修改


	/**
	 * 获取头4条推荐菜品的信息
	 * 
	 * @return 头4条推荐菜品列表
	 * */
	public ArrayList<OrderInfo> getTop4RecommendOrders() {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 查询头4条推荐的菜品信息
		ArrayList<OrderInfo> list = helper.preparedForPageList(
				"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId",
				new Object[] {}, 1, 4, OrderInfo.class);
		System.out.println(list.get(0).getTableId());
		// 返回结果
		return list;

	}
	
	
	
	public ArrayList<OrderInfo> getSomeRecommendOrders(int page) {
		// 获取带有连接池的数据库模版操作工具对象
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// 查询头4条推荐的菜品信息
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId",
						new Object[] {}, page, 4, OrderInfo.class);
				
				// 返回结果
				return list;

	}
	
	public ArrayList<OrderInfo> getSomeRecommendOrders_0(int page) {
		// 获取带有连接池的数据库模版操作工具对象
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// 查询头4条推荐的菜品信息
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,dishesinfo.dishesPrice,dishesinfo.dishesId,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				System.out.println(list.get(0).getDishesName()+"~");
				// 返回结果
				return list;

	}
	
	public int getFinalPage_1() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<OrderInfo> list = helper.queryForList(
				"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,dishesinfo.dishesPrice,dishesinfo.dishesId,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0"
				,
				OrderInfo.class);
		
		int t=list.size();
		System.out.println(t+"aaa");
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}
	
	
	public int getFinalPage_0() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<OrderInfo> list = helper.queryForList(
				"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and orderinfo.orderState=0",
				OrderInfo.class);
		
		int t=list.size();
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}
	
	
	
	public int getFinalPage() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<OrderInfo> list = helper.queryForList("select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId",
				OrderInfo.class);
		
		int t=list.size();
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}
	public ArrayList<OrderInfo> getSomeRecommendOrders_operateData(int page) {
		// 获取带有连接池的数据库模版操作工具对象
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// 查询头4条推荐的菜品信息
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0 group by orderId"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				//System.out.println(list.get(0).getDishesName()+"~");
				// 返回结果
				return list;

	}
	
	public int getFinalPage_operateData() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<OrderInfo> list = helper.queryForList(
				"select orderinfo.tableId,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0 group by orderId"
				,
				OrderInfo.class);
		
		int t=list.size();
		System.out.println(t+"aaa");
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}
	public void modifyOrders_2(int id) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执行修改操作
			helper.executePreparedUpdate(
				"update orderinfo set orderState=2 where orderId=?",
				new Object[] {id});

	}
	
	public void modifyOrders_3(int id) {
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执行修改操作
			helper.executePreparedUpdate(
				"update orderinfo set orderState=3 where orderId=?",
				new Object[] {id});

	}
	
	public ArrayList<OrderInfo> getSomeRecommendOrders_toAdminCharge(int page) {
		// 获取带有连接池的数据库模版操作工具对象
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// 查询头4条推荐的菜品信息
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"SELECT userAccount,orderId,tableId,orderBeginDate,orderEndDate,SUM(orderdishes.num*dishesinfo.dishesPrice)MONEY FROM dishesinfo,orderdishes,orderinfo,`userinfo` WHERE orderinfo.orderId=orderdishes.orderReference AND orderdishes.dishes=dishesinfo.dishesId AND `orderinfo`.`waiterId`=`userinfo`.`userId` AND `orderinfo`.`orderState` = 1 GROUP BY orderinfo.orderId"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				//System.out.println(list.get(0).getDishesName()+"~");
				// 返回结果
				return list;

	}
	
	public int getFinalPage_toAdminCharge() {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		
		ArrayList<OrderInfo> list = helper.queryForList(
				"SELECT userAccount,orderId,tableId,orderBeginDate,orderEndDate,SUM(orderdishes.num*dishesinfo.dishesPrice)MONEY FROM dishesinfo,orderdishes,orderinfo,`userinfo` WHERE orderinfo.orderId=orderdishes.orderReference AND orderdishes.dishes=dishesinfo.dishesId AND `orderinfo`.`waiterId`=`userinfo`.`userId` AND `orderinfo`.`orderState` = 1 GROUP BY orderinfo.orderId"
				,
				OrderInfo.class);
		
		int t=list.size();
		System.out.println(t+"aaa");
		int page = (t/4)+(t%4==0?0:1);

		return page;

	}

	public ArrayList<OrderInfo> getSomeRecommendOrders_detailDishes() {
		// 获取带有连接池的数据库模版操作工具对象
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// 查询头4条推荐的菜品信息
				ArrayList<OrderInfo> list = helper.queryForList(
						"select orderdishes.orderReference ,dishesinfo.dishesPrice,dishesinfo.dishesName,orderdishes.num from orderdishes,dishesinfo where dishesinfo.dishesId=orderdishes.dishes",
						 OrderInfo.class);
				System.out.println(list.get(0).getOrderReference()+"~");
				// 返回结果
				return list;

	}
}
