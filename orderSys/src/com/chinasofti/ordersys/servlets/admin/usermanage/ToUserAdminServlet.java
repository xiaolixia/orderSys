package com.chinasofti.ordersys.servlets.admin.usermanage;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.service.admin.UserService;
import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.web.upload.MultipartRequestParser;


public class ToUserAdminServlet extends  HttpServlet{


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
		

		UserService service = new UserService();
		int page = Integer.parseInt(request.getParameter("page"));
		if(page==-1){
			page = service.getFinalPage();
		}
		if(page<1)page=1;
		if(page>service.getFinalPage())page=service.getFinalPage();
		ArrayList<UserInfo> list = service.getSomeRecommendUser(page);
		request.setAttribute("glist", list);
		request.setAttribute("page", page);

	
		request.getRequestDispatcher("/pages/admin/useradmin.jsp").forward(request,
				response);
	}

}