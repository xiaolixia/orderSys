package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.service.kitchen.OrderService;
import com.chinasofti.ordersys.vo.DishesInfo;

public class payMoneyServlet extends HttpServlet  {



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

			int id=Integer.parseInt(request.getParameter("orderId"));
//			System.out.println(id);
			OrderService service = new OrderService();
			service.modifyOrders(id);
			request.setAttribute("page", 1);
//			request.getRequestDispatcher("/OrderSys/toadminmain.order").forward(request,response);
		//	response.sendRedirect("/OrderSys/tooperatedata.order?page=1");
		}

}