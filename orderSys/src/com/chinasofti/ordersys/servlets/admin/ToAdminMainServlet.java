/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.admin;

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
 * Description: ��ת����Ա���������תServlet
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
public class ToAdminMainServlet extends HttpServlet {



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
		DishesService service = new DishesService();
		// ��ȡͷ4���Ƽ���Ʒ��Ϣ�б�
		int line = Integer.parseInt(request.getParameter("page"));
		if(line==-1){
			line=service.getFinalPage();
		}
		if(line<1){line=1;}
		if(line>service.getFinalPage()){line=service.getFinalPage();}
		
		System.out.println(line+" 1 "+service.getFinalPage());
		ArrayList<DishesInfo> list = service.getSomeRecommendDishes(line);
		request.setAttribute("glist", list);
		request.setAttribute("page", line);
		
		// ֱ��������ת���ķ�ʽ��ת������Ա������
		request.getRequestDispatcher("/pages/admin/main.jsp").forward(request,
				response);
	}



}
