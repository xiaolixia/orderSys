package com.chinasofti.ordersys.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.web.upload.MultipartRequestParser;
import com.chinasofti.ordersys.service.DomainProtectedService;
import com.chinasofti.ordersys.service.login.LoginService;
import com.chinasofti.util.sec.Passport;

public class CheckUserPassServlet extends  HttpServlet{


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
        int userId = Integer.parseInt(request.getParameter("userId"));
        System.out.println(request.getParameter("userPass"));
        UserService service = new UserService();
		// 设置返回MIME类型
		response.setContentType("text/html");
		// 获取针对客户端的文本输出流
		PrintWriter out = response.getWriter();
		out.print(service.checkUserPass(userId,request.getParameter("userPass")));	
		// 刷新输出流
		/*out.flush();*/
		// 关闭输出流
		out.close();
	}

}