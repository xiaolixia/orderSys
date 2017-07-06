package com.chinasofti.ordersys.servlets.query;



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
 * Description: 锟斤拷转锟斤拷锟斤拷员锟斤拷锟斤拷锟斤拷锟斤拷锟阶猄ervlet
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
public class ToQueryServlet extends HttpServlet {



	/**
	 * 锟斤拷锟斤拷GET锟斤拷式锟斤拷锟斤拷Servlet时锟斤拷service锟斤拷锟斤拷锟截碉拷,锟斤拷Servlet锟斤拷同锟斤拷锟斤拷锟斤拷要锟斤拷同锟斤拷锟斤拷应锟斤拷锟斤拷锟斤拷诒锟絊ervlet锟斤拷直锟接碉拷锟斤拷doPost
	 * 锟斤拷锟皆憋拷证锟斤拷应锟斤拷一锟斤拷锟斤拷
	 * 
	 * @param request
	 *            锟斤拷锟斤拷锟斤拷锟�
	 * @param response
	 *            锟斤拷应锟斤拷锟斤拷
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 锟斤拷锟斤拷Post锟斤拷式锟斤拷锟斤拷Servlet时锟斤拷service锟斤拷锟斤拷锟截碉拷 
	 * 
	 * @param request
	 *            锟斤拷锟斤拷锟斤拷锟�
	 * @param response
	 *            锟斤拷应锟斤拷锟斤拷
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DishesService service = new DishesService();
		int page = Integer.parseInt(request.getParameter("page"));
		if(page==-1){
			page = service.getFinalPage();
		}
		ArrayList<DishesInfo> list = service.getSomeRecommendDishes(page);
		request.setAttribute("glist", list);
		request.setAttribute("page", page);
		System.out.println(page);
		response.sendRedirect("/OrderSys/toadminmain.order?page="+page);
	}



}
