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
public class ToAdminChargeServlet extends HttpServlet {

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
		// ֱ��������ת���ķ�ʽ��ת������������
		// ������Ʒ����������
				OrderService service = new OrderService();
				// ��ȡͷ4���Ƽ���Ʒ��Ϣ�б�
				int line = Integer.parseInt(request.getParameter("page"));
				if(line==-1){
					line=service.getFinalPage_toAdminCharge();
				}
				if(line<1){line=1;}
				if(line>service.getFinalPage_toAdminCharge()){line=service.getFinalPage_toAdminCharge();}

				System.out.println(line+" 1 "+service.getFinalPage_toAdminCharge());
				ArrayList<OrderInfo> list = service.getSomeRecommendOrders_toAdminCharge(line);
				request.setAttribute("glist", list);
				request.setAttribute("page", line);
				System.out.println(line);
				
				ArrayList<OrderInfo> list_dish = service.getSomeRecommendOrders_detailDishes();
				System.out.println(list_dish.get(0).getOrderReference());
				request.setAttribute("disheslist", list_dish);
				
				
				
				
				
				
				// ������Ʒ����������
				DishesService service1 = new DishesService();
				// ��ȡͷ4���Ƽ���Ʒ��Ϣ�б�
				ArrayList<DishesInfo> list1 = service1.getTop4RecommendDishes();
				request.setAttribute("glist1", list1);
				
				
				
		
				
				
				request.getRequestDispatcher("/pages/admin/adminCharge.jsp?page=1").forward(
				request, response);
	}
}
