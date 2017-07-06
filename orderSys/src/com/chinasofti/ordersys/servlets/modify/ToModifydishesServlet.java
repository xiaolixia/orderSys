/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.modify;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.vo.DishesInfo;
/**
 * <p>
 * Title:ToAdminMainServlet
 * </p>
 * <p>
 * Description: 修改菜的属性Servlet
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
public class ToModifydishesServlet extends HttpServlet {



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
			int id = Integer.parseInt(request.getParameter("dishesId"));
			
			// 创建菜品管理服务对象
			DishesService service = new DishesService();
			DishesInfo dish = service.getDishesById(id);
			// 获取头4条推荐菜品信息列表
			
			request.setAttribute("chosendish", dish);
			// 直接以请求转发的方式跳转到管理员主界面
				request.getRequestDispatcher("/pages/admin/modifydishes.jsp").forward(request,
					response);
	}



}
