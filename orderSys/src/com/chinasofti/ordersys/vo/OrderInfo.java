/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.vo;

import java.sql.Date;

/**
 * <p>
 * Title:OrderInfo
 * </p>
 * <p>
 * Description: ������ϢVO
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
public class OrderInfo {
	/**
	 * ����ID
	 * */
	private int orderId;
	/**
	 * ����ʱ��
	 * */
	private Date orderBeginDate;
	/**
	 * �ᵥʱ��
	 * */
	private Date orderEndDate;
	/**
	 * �㵥����ԱID
	 * */
	private float money;
	public float getMoney() {
		return money;
	}
	private int orderReference;
	public int getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(int orderReference) {
		this.orderReference = orderReference;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	private int waiterId;
	/**
	 * ��������
	 * */
	private int tableId;
	/**
	 * �㵥����Ա�ʺ�
	 * */
	private String userAccount;
	/**
	 * ��Ʒ����
	 * */
	private float dishesPrice;
	/**
	 * ��Ʒ��
	 * */
	private String dishesName;

	public String getDishesName() {
		return dishesName;
	}

	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}

	public float getDishesPrice() {
		return dishesPrice;
	}

	public void setDishesPrice(float dishesPrice) {
		this.dishesPrice = dishesPrice;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**
	 * ����״̬
	 * */
	private int orderState;
	/**
	 * ��ƷID
	 * */
	private int dishes;
	/**
	 * ��Ʒ����
	 * */
	private int num;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderBeginDate() {

		return orderBeginDate;
	}

	public void setOrderBeginDate(Date orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public int getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public int getDishes() {
		return dishes;
	}

	public void setDishes(int dishes) {
		this.dishes = dishes;
	}
	private int dishesId;
	
	public int getDishesId(){
		return this.dishesId;
	}
	public void setDishesId(int dishesId) {
		this.dishesId = dishesId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
