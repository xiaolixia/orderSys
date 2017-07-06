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
		DishesService service = new DishesService();
		MultipartRequestParser parser = new MultipartRequestParser();
		// ������ȡDishesInfo��Ʒ��Ϣ����
		DishesInfo info = (DishesInfo) parser.parse(request,
				DishesInfo.class);
		String checkboxes[]=request.getParameterValues("recommend");
		if(checkboxes!=null){
			info.setRecommend(1);
		}else {
			info.setRecommend(0);
		}
		// ִ�в�Ʒ��Ϣ�޸Ĺ���
		service.addDishes(info);
		// ��ת����Ʒ�������
		response.sendRedirect("/OrderSys/toadminmain.order?page=1");
	}

}