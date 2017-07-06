/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.admin.usermanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.web.upload.MultipartRequestParser;

import com.chinasofti.util.sec.Passport;

/**
 * <p>
 * Title:ToModifyDishesServlet
 * </p>
 * <p>
 * Description: 跳转修改菜品界面的跳转Servlet
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
public class ModifyUserServlet extends HttpServlet {

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
		
		// 创建菜品管理服务对象
		UserService service = new UserService();
		// 创建表单请求解析器工具
		MultipartRequestParser parser = new MultipartRequestParser();
		// 解析获取DishesInfo菜品信息对象
		UserInfo info = (UserInfo) parser.parse(request,
				UserInfo.class);
		Passport passport = new Passport();
		// 将用户输入的密码用md5码方式加密
		info.setUserPass(passport.md5(request.getParameter("userOldPass")));

		//System.out.println(info.getUserAccount()+"aaaa"+request.getParameter("userAccount")+"e "+info.getUserId());
		//info.setRoleId(info.getRole());
		// 执行菜品信息修改工作
		service.modifyUser(info);
		request.setAttribute("page", 1);
		// 跳转到菜品管理界面
		response.sendRedirect("/OrderSys/touseradmin.order?page=1");

	}

}
