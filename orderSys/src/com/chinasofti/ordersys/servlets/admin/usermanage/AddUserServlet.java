package com.chinasofti.ordersys.servlets.admin.usermanage;

import java.io.IOException;

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

public class AddUserServlet extends  HttpServlet{


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
		UserService service = new UserService();
		MultipartRequestParser parser = new MultipartRequestParser();
		
		// 解析获取UserInfo菜品信息对象
		UserInfo info = (UserInfo) parser.parse(request,
				UserInfo.class);
		String psw=request.getParameter("userOldPass");
		int roleid=Integer.parseInt(request.getParameter("roleId"));
		info.setRoleId(roleid);
		info.setRole(roleid);
		info.setUserPass(psw);
		info.setLocked(0);
		// 获取数据加密工具对象
		Passport passport = new Passport();
		// 将用户输入的密码用md5码方式加密
		info.setUserPass(passport.md5(info.getUserPass()));
		
		
//		先默认传个图片
		info.setFaceimg("default.jpg");
		
	
		
		System.out.println("11");
		// 执行菜品信息修改工作
		service.addUser(info);
		// 跳转到菜品管理界面
		response.sendRedirect("/OrderSys/touseradmin.order?page=1");
	}

}