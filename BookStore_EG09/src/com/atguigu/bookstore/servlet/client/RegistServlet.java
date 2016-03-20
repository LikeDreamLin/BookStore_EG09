package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理注册业务的Servlet
 * @author lilichao
 *
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个UserService
	private UserService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户发送的信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		//将用户信息封装为一个USer对象
		User user = new User(null, username, password, email);
		
		//调用Service将User插入进数据库
		boolean regist = userService.regist(user);
		
		//判断注册是否成功
		if(regist){
			//注册成功，重定向到regist-success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else{
			
			//注册失败，设置一个错误消息
			request.setAttribute("msg", "用户名已存在！");
			
			//注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

}
