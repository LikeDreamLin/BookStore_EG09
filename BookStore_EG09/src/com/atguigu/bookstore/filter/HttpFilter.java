package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpFilter用来作为Filter的父类，简化Filter的开发(理解)
 * @author lilichao
 *
 */
public abstract class HttpFilter implements Filter {
	
	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		
		//调用无参的init
		this.init();
	}
	
	/**
	 * 定义一个无参的init方法，用来做初始化操作
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		
	}
	
	public FilterConfig getConfig() {
		return config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//doFilter里的request和response都是ServletRequest或ServletResponse
		//这个类型的对象有很多方法不能调用，我们希望这个两个对象的类型是Http相关的
		
		//将两个对象强转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//调用抽象的doFilter
		doFilter(req, resp, chain);
		

	}
	
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException;

	@Override
	public void destroy() {

	}

}
