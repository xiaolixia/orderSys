/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.servlets.modify;

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
 * Description: �޸Ĳ˵�����Servlet
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
public class ModifyInfoServlet extends HttpServlet {



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
		
//		System.out.println(Integer.parseInt(request.getParameter("dishesId")) + request.getParameter("dishName"));
		
		
//	
//		int id=(Integer.parseInt(request.getParameter("dishesId")));
////		String id = request.getParameter("dishesId");
//		String name=(request.getParameter("dishName"));
//		String discript=(request.getParameter("dishDiscription"));
//		float price=(Float.parseFloat(request.getParameter("dishPrice")));
//////dishesImg=${temp.dishesImg}&dishesTxt=${temp.dishesTxt}&recommend=${temp.recommend}
//		String img=(request.getParameter("dishesImg"));
//		String txt=(request.getParameter("dishesTxt"));
//		int recommend=(Integer.parseInt(request.getParameter("recommend")));
////		String recommend=request.getParameter("recommend");
////		
//		
//		System.out.println(id+"  !  " +name+"  !  " +discript+"  !  " +price+"  !  " +img+"  !  " +txt+"  !  " +recommend);
		
//		
		DishesInfo info = new DishesInfo();
		info.setDishesId(Integer.parseInt(request.getParameter("dishesId")));
		info.setDishesName(request.getParameter("dishName"));
		info.setDishesDiscript(request.getParameter("dishDiscription"));
		info.setDishesPrice(Float.parseFloat(request.getParameter("dishPrice")));
//dishesImg=${temp.dishesImg}&dishesTxt=${temp.dishesTxt}&recommend=${temp.recommend}
		info.setDishesImg(request.getParameter("dishesImg"));
		info.setDishesTxt(request.getParameter("dishesTxt"));
		info.setRecommend(Integer.parseInt(request.getParameter("recommend")));
		System.out.print(info);
		DishesService service = new DishesService();
		service.modifyDishes(info);
//		request.getRequestDispatcher("/pages/admin/main.jsp").forward(request,response);
		response.sendRedirect("/OrderSys/toadminmain.order");
	}



}
