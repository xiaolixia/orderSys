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


		// ������Ʒ����������
		UserService service = new UserService();
		MultipartRequestParser parser = new MultipartRequestParser();
		
		// ������ȡUserInfo��Ʒ��Ϣ����
		UserInfo info = (UserInfo) parser.parse(request,
				UserInfo.class);
		String psw=request.getParameter("userOldPass");
		int roleid=Integer.parseInt(request.getParameter("roleId"));
		info.setRoleId(roleid);
		info.setRole(roleid);
		info.setUserPass(psw);
		info.setLocked(0);
		// ��ȡ���ݼ��ܹ��߶���
		Passport passport = new Passport();
		// ���û������������md5�뷽ʽ����
		info.setUserPass(passport.md5(info.getUserPass()));
		
		
//		��Ĭ�ϴ���ͼƬ
		info.setFaceimg("default.jpg");
		
	
		
		System.out.println("11");
		// ִ�в�Ʒ��Ϣ�޸Ĺ���
		service.addUser(info);
		// ��ת����Ʒ�������
		response.sendRedirect("/OrderSys/touseradmin.order?page=1");
	}

}