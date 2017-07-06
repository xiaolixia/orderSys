package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.kitchen.OrderService;
import com.chinasofti.ordersys.service.waiters.OrderDishesService;
import com.chinasofti.ordersys.service.waiters.StaticCartService;
import com.chinasofti.ordersys.vo.Cart;
import com.chinasofti.ordersys.vo.CartUnits;
import com.chinasofti.ordersys.vo.CartUnits.CartEveryUnits;
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
public class AjaxAddCartServlet extends HttpServlet {

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
		OrderDishesService service = new OrderDishesService();
		
		HttpSession session = request.getSession();
		int dishId = Integer.parseInt(request.getParameter("dishesId"));
		int tableId = Integer.parseInt(request.getParameter("tableId"));
		int num = Integer.parseInt(request.getParameter("num"));
//		int ajax = Integer.parseInt(request.getParameter("ajax"));
	//	System.out.println(session.getAttribute("cart"));
		
//		CartUnits cartUnits = new CartUnits();
//		cartUnits.addCartUnits(dishId, dishId, num);
		
		StaticCartService cartService=new StaticCartService();
//		ArrayList<CartEveryUnits> unit = cartUnits.getCartUnits();
		cartService.setCartUnit(dishId, dishId, num);
		ArrayList<CartEveryUnits> unit = cartService.getCartUnit();
			
		response.setContentType("text/html");
		// ��ȡ��Կͻ��˵��ı������
		PrintWriter out = response.getWriter();
		out.print(unit.size()+"add success!");
		//session.setAttribute("cart", cart);
		
		out.flush();
		out.close();
	}

	

}

