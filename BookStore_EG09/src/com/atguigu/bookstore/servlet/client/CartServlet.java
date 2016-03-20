package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;
import com.google.gson.Gson;

/**
 * 处理购物车相关请求的Servlet
 * @author lilichao
 *
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个BookService
	private BookService bookService = new BookServiceImpl();
	
	/**
	 * 更新购物项的数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取图书的id
		String bookId = request.getParameter("bookId");
		//获取一个数量
		String countStr = request.getParameter("count");
		
		//获取购物车
		Cart cart = WEBUtils.getCart(request);
		//修改数量
		cart.updateCount(bookId, countStr);
		
		//这里有三个值需要发送给页面totalCount totalAmount amount
		//总数量
		int totalCount = cart.getTotalCount();
		
		//总金额
		double totalAmount = cart.getTotalAmount();
		
		//图书小计金额
		CartItem cartItem = cart.getMap().get(bookId);
		double amount = cartItem.getAmount();
		
		//创建一个Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount+"");
		map.put("totalAmount", totalAmount+"");
		map.put("amount", amount+"");
		
		//将map转换为一个json
		String json = new Gson().toJson(map);
		
		//将json作为响应发送
		response.getWriter().print(json);
		
	
	}
	
	/**
	 * 从购物车中删除一个购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void del(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取bookId
		String bookId = request.getParameter("bookId");
		
		//获取购物车
		Cart cart = WEBUtils.getCart(request);
		
		//从购物车中移除指定的购物项
		cart.delCartItem(bookId);
		
		//重定向到购物车页面
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
		
	}
	
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取cart
		Cart cart = WEBUtils.getCart(request);
		//清空购物车
		cart.clear();
		//重定向index.jsp
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
	}

	/**
	 * 向购物车中添加商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取HttpSession
		HttpSession session = request.getSession();
		//从session中获取购物车
		Cart cart = WEBUtils.getCart(request);
		
		//获取图书的id
		String bookId = request.getParameter("bookId");
		//根据id查询图书的对象
		Book book = bookService.getBookById(bookId);
		
		//将book的名字设置到域对象
		session.setAttribute("title", book.getTitle());
		
		//将图书添加到购物车中
		cart.addBook2Cart(book);
		
		//获取referer这个头
		String referer = request.getHeader("referer");
		
		//哪来的回哪去
		response.sendRedirect(referer);
		
		
	}

}
