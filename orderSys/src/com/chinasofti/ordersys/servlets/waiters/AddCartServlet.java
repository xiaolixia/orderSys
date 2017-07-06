/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.kitchen.OrderService;
import com.chinasofti.ordersys.service.waiters.OrderDishesService;
import com.chinasofti.ordersys.service.waiters.StaticCartService;
import com.chinasofti.ordersys.vo.Cart;
/**
 * <p>
 * Title:ToWaiterMainServlet
 * </p>
 * <p>
 * Description: 跳转服务员主界面的跳转Servlet
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
public class AddCartServlet extends HttpServlet {

	/**
	 * 当以GET方式请求Servlet时由service方法回调,本Servlet不同方法不要求不同的响应，因此在本Servlet中直接调用doPost
	 * ，以保证响应的一致性
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 当以Post方式请求Servlet时由service方法回调
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int ajax=Integer.parseInt(request.getParameter("ajax"));
		int dishId = Integer.parseInt(request.getParameter("dishesId"));
		int tableId = Integer.parseInt(request.getParameter("tableId"));
		int num = Integer.parseInt(request.getParameter("num"));
		int waiterId = Integer.parseInt(request.getParameter("waiterId"));
		System.out.println(tableId);
		if(ajax==0){
			OrderDishesService service = new OrderDishesService();

			Cart cart;
			if(session.getAttribute("cart")==null){

				cart = new Cart();
			}
			else {
				cart = (Cart) session.getAttribute("cart");
			}/**/
			if(tableId==-1){
				//读取cart进行添加

				cart.addUnit(dishId, num);
				response.setContentType("text/html");
				// 获取针对客户端的文本输出流
				PrintWriter out = response.getWriter();
				session.setAttribute("cart", cart);
				Cart t = (Cart) session.getAttribute("cart");

				out.print(cart.getUnits().size()+"dishes add success!");

				
				out.flush();
				out.close();

			}else{
				//写数据库
				//			System.out.println(cart.getUnits().get(0).getNum());
				cart.setTableId(tableId);
				int orderId = service.getInsertOrderId(cart,waiterId);
				
				service.addOrderDishes(cart, orderId);
				session.removeAttribute("cart");
				request.getRequestDispatcher("towaitermain.order?page=1").forward(
						request, response);
				//	service.addOrder(cart);
			}
			//	request.setAttribute("glist", list);
		



			
		}
		else{
			
			StaticCartService cartService=new StaticCartService();
			
			
			//cartService.removeByTableId(tableId);;
			request.getRequestDispatcher("towaitermain.order?page=1").forward(
					request, response);
		}
	}

	

}
