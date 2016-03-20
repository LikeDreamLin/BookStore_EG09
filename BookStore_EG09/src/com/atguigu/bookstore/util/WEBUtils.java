package com.atguigu.bookstore.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;

/**
 * WEB相关操作的工具类
 * @author lilichao
 *
 */
public class WEBUtils {

	/**
	 * 将请求参数转换为一个Java对象
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T>T param2Bean(HttpServletRequest request, T t) {
		
		//获取所有请求参数的map
		Map map = request.getParameterMap();
		
		try {
			//将map中的参数封装到对象中
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回对象
		return t;
	}

	public static String getPath(HttpServletRequest request) {
		
		//要动态获取这个地址
		///BookStore_EG05/manager/BookManagerServlet?method=findBook&pageNumber=4
		
		
		//获取用户的请求地址
		// /BookStore_EG05/manager/BookManagerServlet
		String requestURI = request.getRequestURI();
		
		//获取用户的查询字符串 method=findBook&pageNumber=2
		String queryString = request.getQueryString();
		
		//截取queryString中的pageNumber
		//判断queryString是否包含pageNumber
		if(queryString.contains("&pageNumber")){
			queryString = queryString.substring(0, queryString.indexOf("&pageNumber"));
		}
		
		return requestURI+"?"+queryString;
	}

	/**
	 * 获取购物车对象
	 * @param request
	 * @return
	 */
	public static Cart getCart(HttpServletRequest request) {
		
		//获取HttpSession
		HttpSession session = request.getSession();
		//从session中获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//判断cart是否为null
		if(cart == null){
			//创建一个新的购物车对象
			cart = new Cart();
			//将cart放入到session中
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}

}
