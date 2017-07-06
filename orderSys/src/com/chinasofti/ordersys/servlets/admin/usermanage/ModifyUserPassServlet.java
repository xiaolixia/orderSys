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
import com.chinasofti.util.sec.Passport;
import com.chinasofti.util.web.upload.MultipartRequestParser;

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
public class ModifyUserPassServlet extends HttpServlet {

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
//		System.out.println("1");
		UserService service = new UserService();
		// �������������������
		MultipartRequestParser parser = new MultipartRequestParser();
		// ������ȡDishesInfo��Ʒ��Ϣ����
		UserInfo info = (UserInfo) parser.parse(request,
				UserInfo.class);
		
		info.setRole(info.getRoleId());
		System.out.println(info.getFaceimg());
		// ��ȡ���ݼ��ܹ��߶���
		Passport passport = new Passport();
		// ���û������������md5�뷽ʽ����
		info.setUserPass(passport.md5(info.getUserPass()));
		
		// ִ�в�Ʒ��Ϣ�޸Ĺ���
		service.modifyUserPasswd(info);
		request.setAttribute("page", 1);
		int role = info.getRoleId();
		
		// ��ת����Ʒ�������
		if(role==2){
			response.sendRedirect("/OrderSys/tokitchenmain.order?page=1");
		}
		if(role==1){
			
			response.sendRedirect("/OrderSys/toadminmain.order?page=1");
		}
		if(role==3){
			response.sendRedirect("/OrderSys/towaitermain.order?page=1");
		}
	}

}
