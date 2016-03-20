package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门被其他的Servlet继承的父类
 * 
 * @author lilichao
 * 
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//post请求乱码要在request.getParameter()第一次调用之前设置编码
		request.setCharacterEncoding("utf-8");

		// 获取用户发送的method请求参数
		String methodName = request.getParameter("method");

		try {
			// 根据方法名去获取方法的对象
			// getDeclaredMethod用来获取当前类的方法对象的
			// 他需要两个参数，第一个参数是方法名 ， 第二个参数是方法的参数列表，参数列表具体穿的的就是参数的类型
			Method method = this.getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);

			// 设置一下访问权限
			method.setAccessible(true);

			// 调用方法
			// invoke()用来执行一个方法
			// 这个方法需要两个参数，第一个参数是要调用哪个对象的方法
			// 第二个参数，是一个可变参数，传调用方法需要的参数
			method.invoke(this, request, response);

		} catch (Exception e) {
			//转换为运行时异常向上抛
			throw new RuntimeException(e);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
