package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.vo.DishesInfo;
import com.chinasofti.util.web.upload.MultipartRequestParser;

public class AddDishesServlet extends  HttpServlet{


	/**
	 * 当以GET方式请求Servlet时由service方法回调,本Servlet不同方法不要求不同的响应，因此在本Servlet中直接调用doPost
	 * ，以保证响应的一致性
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 当以Post方式请求Servlet时由service方法回调
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// 创建菜品管理服务对象
		DishesService service = new DishesService();
		MultipartRequestParser parser = new MultipartRequestParser();
		// 解析获取DishesInfo菜品信息对象
		DishesInfo info = (DishesInfo) parser.parse(request,
				DishesInfo.class);
		String checkboxes[]=request.getParameterValues("recommend");
		if(checkboxes!=null){
			info.setRecommend(1);
		}else {
			info.setRecommend(0);
		}
		// 执行菜品信息修改工作
		service.addDishes(info);
		// 跳转到菜品管理界面
		response.sendRedirect("/OrderSys/toadminmain.order?page=1");
	}

}