package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 用来检查用户是否登录
 * @author lilichao
 *
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取HttpSession
		HttpSession session = request.getSession();
		//获取session中loginUser
		User user = (User)session.getAttribute("loginUser");
		//检查user是否为null
		if(user == null){
			//没有登录,设置一个错误消息
			request.setAttribute("msg", "请登录以后在操作！");
			//转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//已登录，直接放行请求
			chain.doFilter(request, response);
		}

	}

}
