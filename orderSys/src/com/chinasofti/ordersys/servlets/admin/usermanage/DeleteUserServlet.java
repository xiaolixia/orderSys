package com.chinasofti.ordersys.servlets.admin.usermanage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.DishesService;
import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.DishesInfo;

public class DeleteUserServlet extends HttpServlet  {



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

			int id=Integer.parseInt(request.getParameter("userId"));
//			System.out.println(id);
			UserService service = new UserService();
			service.deleteUserById(id);
			request.setAttribute("page", 1);
//			request.getRequestDispatcher("/OrderSys/toadminmain.order").forward(request,response);
			response.sendRedirect("/OrderSys/touseradmin.order?success="+id+"&page=1");
		}



	
}