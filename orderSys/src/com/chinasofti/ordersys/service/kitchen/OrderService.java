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
 * Description: ��Ʒ��������������
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
	 * ���ݲ�ƷIDֵɾ����Ʒ��Ϣ�ķ���
	 * 
	 * @param dishesId
	 *            Ҫɾ���Ĳ�ƷId
	 * */
	public void deleteDishesById(Integer dishesId) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ִ��ɾ������
		helper.executePreparedUpdate("delete from dishesinfo where dishesId=?",
				new Object[] { dishesId });
	}

	/**
	 * ��Ӳ�Ʒ�ķ���
	 * 
	 * @param info
	 *            ��Ҫ��ӵĲ�Ʒ��Ϣ
	 * */
	public void addDishes(DishesInfo info) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ִ����Ӳ���
		helper.executePreparedUpdate(
				"insert into dishesinfo(dishesName,dishesDiscript,dishesTxt,dishesImg,recommend,dishesPrice) values(?,?,?,?,?,?)",
				new Object[] { info.getDishesName(), info.getDishesDiscript(),
						info.getDishesTxt(), info.getDishesImg(),
						new Integer(info.getRecommend()),
						new Float(info.getDishesPrice()) });

	}
	
	/**
	 * �޸Ĳ�Ʒ��Ϣ�ķ���
	 * 
	 * @param Info
	 *            Ҫ�޸ĵĲ�Ʒ��Ϣ������dishesIdΪ�޸����ݣ�������ϢΪ�޸ĵ�Ŀ��ֵ
	 * */
	public void modifyOrders(int id) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy=MM-dd HH:mm:ss");
		// ִ���޸Ĳ���
			helper.executePreparedUpdate(
				"update orderinfo set orderState=1,orderEndDate=? where orderId=?",
				new Object[] {df.format(new Date()),id});

	}
	
	
	
	
	

	/**
	 * ����dishesId��ȡ��Ʒ��ϸ��Ϣ�ķ���
	 * 
	 * @param dishesId
	 *            Ҫ��ȡ��Ϣ���ض���ƷId
	 * @return ���ظ�id�Ĳ�Ʒ��ϸ��Ϣ
	 * */
	/*	public OrderInfo getOrdersById(Integer dishesId) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ��ѯ����id�Ĳ�Ʒ��ϸ��Ϣ
		ArrayList<OrderInfo> list = helper.preparedQueryForList(
				"select * from dishesinfo where dishesId=?",
				new Object[] { dishesId }, OrderInfo.class);
		// ���ز�ѯ���
		return list.get(0);
	
	}
*///�е����⣬��Ҫ�޸�


	/**
	 * ��ȡͷ4���Ƽ���Ʒ����Ϣ
	 * 
	 * @return ͷ4���Ƽ���Ʒ�б�
	 * */
	public ArrayList<OrderInfo> getTop4RecommendOrders() {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
		ArrayList<OrderInfo> list = helper.preparedForPageList(
				"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId",
				new Object[] {}, 1, 4, OrderInfo.class);
		System.out.println(list.get(0).getTableId());
		// ���ؽ��
		return list;

	}
	
	
	
	public ArrayList<OrderInfo> getSomeRecommendOrders(int page) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderState from orderinfo,dishesinfo,orderdishes where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId",
						new Object[] {}, page, 4, OrderInfo.class);
				
				// ���ؽ��
				return list;

	}
	
	public ArrayList<OrderInfo> getSomeRecommendOrders_0(int page) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,dishesinfo.dishesName,orderdishes.num,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,dishesinfo.dishesPrice,dishesinfo.dishesId,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				System.out.println(list.get(0).getDishesName()+"~");
				// ���ؽ��
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
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"select orderinfo.tableId,orderinfo.orderId,orderinfo.orderBeginDate,orderinfo.orderEndDate,orderinfo.waiterId,userinfo.userAccount,orderinfo.orderState from orderinfo,dishesinfo,orderdishes,userinfo where dishesinfo.dishesId=orderdishes.dishes and orderdishes.orderReference=orderinfo.orderId and userinfo.userId=orderinfo.waiterId and orderinfo.orderState=0 group by orderId"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				//System.out.println(list.get(0).getDishesName()+"~");
				// ���ؽ��
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
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ִ���޸Ĳ���
			helper.executePreparedUpdate(
				"update orderinfo set orderState=2 where orderId=?",
				new Object[] {id});

	}
	
	public void modifyOrders_3(int id) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// ִ���޸Ĳ���
			helper.executePreparedUpdate(
				"update orderinfo set orderState=3 where orderId=?",
				new Object[] {id});

	}
	
	public ArrayList<OrderInfo> getSomeRecommendOrders_toAdminCharge(int page) {
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
				ArrayList<OrderInfo> list = helper.preparedForPageList(
						"SELECT userAccount,orderId,tableId,orderBeginDate,orderEndDate,SUM(orderdishes.num*dishesinfo.dishesPrice)MONEY FROM dishesinfo,orderdishes,orderinfo,`userinfo` WHERE orderinfo.orderId=orderdishes.orderReference AND orderdishes.dishes=dishesinfo.dishesId AND `orderinfo`.`waiterId`=`userinfo`.`userId` AND `orderinfo`.`orderState` = 1 GROUP BY orderinfo.orderId"
						,
						new Object[] {}, page, 4, OrderInfo.class);
				//System.out.println(list.get(0).getDishesName()+"~");
				// ���ؽ��
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
		// ��ȡ�������ӳص����ݿ�ģ��������߶���
				JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
				// ��ѯͷ4���Ƽ��Ĳ�Ʒ��Ϣ
				ArrayList<OrderInfo> list = helper.queryForList(
						"select orderdishes.orderReference ,dishesinfo.dishesPrice,dishesinfo.dishesName,orderdishes.num from orderdishes,dishesinfo where dishesinfo.dishesId=orderdishes.dishes",
						 OrderInfo.class);
				System.out.println(list.get(0).getOrderReference()+"~");
				// ���ؽ��
				return list;

	}
}
