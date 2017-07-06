
/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.service.waiters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.util.resources.CalendarData;

import com.chinasofti.ordersys.vo.Cart;
import com.chinasofti.ordersys.vo.Cart.CartUnit;
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
public class OrderDishesService {


	/**
	 * 添加菜品的方法
	 * 
	 * @param info
	 *            需要添加的菜品信息
	 * */
	public int getInsertOrderId(Cart info,int waiterId) {
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy=MM-dd HH:mm:ss");
		Object[] list = helper.insertForGeneratedKeys(
				"INSERT INTO `ordersys`.`orderinfo` (`orderBeginDate`, `orderEndDate`, `waiterId`, `orderState`, `tableId`) VALUES ( '"+df.format(new Date())+"', null, '"+waiterId+"', '0', '"+info.getTableId()+"')"
			/*	"INSERT INTO orderinfo ( orderBeginDate, orderEndDate, waiterId, orderState, tableId) VALUES ('"
				+"null" +"',null,"
				+/*服务员号*//* "'1'"+",'0','"+ info.getTableId()+"')"      */
				);
		System.out.println("222");
		int len = list.length;
		int id = Integer.parseInt(String.valueOf(list[0]));
		System.out.println(id);
		return id;

	}

	
	
	
	public void addOrderDishes(Cart cart,int id){
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		ArrayList<CartUnit> a = cart.getUnits();
		int len = a.size();
		System.out.println(len+"aaa");
		for(int i =0;i<len;i++){
			helper.executePreparedUpdate(
					"insert into orderdishes(orderReference,dishes,num) values(?,?,?)",
					new Object[] {
							id,a.get(i).getDishesId(),a.get(i).getNum()
					});
		}
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
		// 执行修改操作
			helper.executePreparedUpdate(
				"update orderinfo set orderState=1 where orderId=?",
				new Object[] {id});

	}
	
	
	
	
	


}
