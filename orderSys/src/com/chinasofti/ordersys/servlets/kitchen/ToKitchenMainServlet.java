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
 * Description: ��ת������������תServlet
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
		// ֱ��������ת���ķ�ʽ��ת�����������
		// ������Ʒ����������
				OrderService service = new OrderService();
				// ��ȡͷ4���Ƽ���Ʒ��Ϣ�б�
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
