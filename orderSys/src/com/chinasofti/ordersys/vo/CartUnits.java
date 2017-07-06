package com.chinasofti.ordersys.vo;

import java.util.ArrayList;

public class CartUnits {
	ArrayList<CartEveryUnits> cartUnits = new ArrayList<CartEveryUnits>();
	
	public ArrayList<CartEveryUnits> getCartUnits() {
		return cartUnits;
	}

	public void setCartUnits(ArrayList<CartEveryUnits> cartUnits) {
		this.cartUnits = cartUnits;
	}

	public void addCartUnits(int tid,int did,int num){
		cartUnits.add(new CartEveryUnits(tid,did,num) );
	}
	
	public void removeByTableId(int tableId){
		for(int i=0;i<cartUnits.size();){
		//	System.out.println(cartUnits.get(i).getTableId()+"AAA");
			if(cartUnits.get(i).getTableId()==tableId){
				cartUnits.remove(i);
			}else{i++;}
		}
	}
	
	
	public class CartEveryUnits{
		private  int tableId;
		private int dishesId;
		private int num;
		CartEveryUnits(){}
		CartEveryUnits(int tid,int did,int num){this.tableId=tid;this.dishesId=did;this.num=num;}
		
		public int getTableId() {
			return tableId;
		}
		public void setTableId(int tableId) {
			this.tableId = tableId;
		}
		public int getDishesId() {
			return dishesId;
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
}
