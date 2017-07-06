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
import com.chinasofti.ordersys.vo.CartUnits;
import com.chinasofti.ordersys.vo.CartUnits.CartEveryUnits;
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
public class AjaxAddCartServlet extends HttpServlet {

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
		// 直接以请求转发的方式跳转到服务员主界面
		// 创建菜品管理服务对象
		OrderDishesService service = new OrderDishesService();
		
		HttpSession session = request.getSession();
		int dishId = Integer.parseInt(request.getParameter("dishesId"));
		int tableId = Integer.parseInt(request.getParameter("tableId"));
		int num = Integer.parseInt(request.getParameter("num"));
//		int ajax = Integer.parseInt(request.getParameter("ajax"));
	//	System.out.println(session.getAttribute("cart"));
		
//		CartUnits cartUnits = new CartUnits();
//		cartUnits.addCartUnits(dishId, dishId, num);
		
		StaticCartService cartService=new StaticCartService();
//		ArrayList<CartEveryUnits> unit = cartUnits.getCartUnits();
		cartService.setCartUnit(dishId, dishId, num);
		ArrayList<CartEveryUnits> unit = cartService.getCartUnit();
			
		response.setContentType("text/html");
		// 获取针对客户端的文本输出流
		PrintWriter out = response.getWriter();
		out.print(unit.size()+"add success!");
		//session.setAttribute("cart", cart);
		
		out.flush();
		out.close();
	}

	

}

