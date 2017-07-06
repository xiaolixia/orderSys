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
 * Description: ��ת�޸Ĳ�Ʒ�������תServlet
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
	 * ����GET��ʽ����Servletʱ��service�����ص�,��Servlet��ͬ������Ҫ��ͬ����Ӧ������ڱ�Servlet��ֱ�ӵ���doPost
	 * ���Ա�֤��Ӧ��һ����
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * ����Post��ʽ����Servletʱ��service�����ص�
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ������Ʒ����������
		UserService service = new UserService();
		// �������������������
		MultipartRequestParser parser = new MultipartRequestParser();
		// ������ȡDishesInfo��Ʒ��Ϣ����
		UserInfo info = (UserInfo) parser.parse(request,
				UserInfo.class);
		Passport passport = new Passport();
		// ���û������������md5�뷽ʽ����
		info.setUserPass(passport.md5(request.getParameter("userOldPass")));

		//System.out.println(info.getUserAccount()+"aaaa"+request.getParameter("userAccount")+"e "+info.getUserId());
		//info.setRoleId(info.getRole());
		// ִ�в�Ʒ��Ϣ�޸Ĺ���
		service.modifyUser(info);
		request.setAttribute("page", 1);
		// ��ת����Ʒ�������
		response.sendRedirect("/OrderSys/touseradmin.order?page=1");

	}

}
