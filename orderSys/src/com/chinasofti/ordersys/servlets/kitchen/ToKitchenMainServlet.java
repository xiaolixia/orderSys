/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.kitchen;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.kitchen.OrderService;
import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.OrderInfo;
/**
 * <p>
 * Title:ToKitchenMainServlet
 * </p>
 * <p>
 * Description: 跳转后厨主界面的跳转Servlet
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
public class ToKitchenMainServlet extends HttpServlet {

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
		// 直接以请求转发的方式跳转到后厨主界面
		// 创建菜品管理服务对象
				OrderService service = new OrderService();
				// 获取头4条推荐菜品信息列表
				int line = Integer.parseInt(request.getParameter("page"));
				if(line==-1){
					line=service.getFinalPage_1();
				}
				if(line<1){line=1;}
				if(line>service.getFinalPage_1()){line=service.getFinalPage_1();}

				//System.out.println(line+" 1 "+service.getFinalPage_1());
				ArrayList<OrderInfo> list = service.getSomeRecommendOrders_0(line);
				request.setAttribute("glist", list);
				request.setAttribute("page", line);
				//System.out.println(line);
		request.getRequestDispatcher("/pages/kitchen/kitchenmain.jsp").forward(
				request, response);
	}


}
