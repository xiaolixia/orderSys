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

import com.chinasofti.ordersys.service.admin.UserService;

import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.UserInfo;

/**
 * <p>
 * Title:ToModifyDishesServlet
 * </p>
 * <p>
 * Description: ��ת�޸ĸ�������������תServlet
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
public class ToSelfModifyServlet extends HttpServlet {

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
		// ���������������������
		UserService service = new UserService();
		// ��ȡҪ�޸ĵ���ԱID����ѯ��Ӧ����Ϣ
		UserInfo info = service.getUserById(new Integer(request
				.getParameter("userId")));
		// ��������Ϣ����request������
		request.setAttribute("USER_INFO", info);
		// ��ת��������Ϣ�޸Ľ���
		request.getRequestDispatcher("/pages/users/modifyuser.jsp").forward(
				request, response);
	}

}
