package com.chinasofti.ordersys.service.waiters;

import java.util.ArrayList;

import com.chinasofti.ordersys.vo.CartUnits;
import com.chinasofti.ordersys.vo.CartUnits.CartEveryUnits;

public class StaticCartService {
	static  CartUnits cartUnit = new CartUnits();
	public StaticCartService(){}
	
	
	public static ArrayList<CartEveryUnits> getCartUnit() {
		return cartUnit.getCartUnits();
	}
	public static void setCartUnit(int tid,int did,int num) {
		StaticCartService.cartUnit.addCartUnits(tid, did, num);;
	}
	
	public void removeByTableId(int tableId){
		cartUnit.removeByTableId(tableId);
	}
	
}
