/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.ordersys.vo.Cart;
/**
 * <p>
 * Title:ToWaiterMainServlet
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
public class ToWaiterMainServlet extends HttpServlet {

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
		// ֱ��������ת���ķ�ʽ��ת������Ա������
		// ������Ʒ����������
		DishesService service = new DishesService();
		// ��ȡͷ4���Ƽ���Ʒ��Ϣ�б�
		int offset=8;
		int line = Integer.parseInt(request.getParameter("page"));
		if(line==-1){
			line=service.getFinalPageoffset(offset);
		}
		if(line<1){line=1;}
		if(line>service.getFinalPageoffset(offset)){line=service.getFinalPageoffset(offset);}
		

		ArrayList<DishesInfo> list = service.getSomeRecommendDishes(line*2-1);
		ArrayList<DishesInfo> list2 = service.getSomeRecommendDishes(line*2);
		request.setAttribute("glist", list);
		request.setAttribute("glist2", list2);
		request.setAttribute("page", line);

		
		
		request.getRequestDispatcher("/pages/waiters/takeorder.jsp").forward(
				request, response);
	}

	

}
