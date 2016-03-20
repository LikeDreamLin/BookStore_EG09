package com.atguigu.bookstore.servlet.manager;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理后台的订单的相关业务
 * @author lilichao
 *
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	//创建一个OrderService
	private OrderService orderService = new OrderServiceImpl();
	
	
	
	/**
	 * 发货的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void sendBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要发货的订单号
		String orderId = request.getParameter("orderId");
		
		//调用service进行发货操作
		orderService.sendBook(orderId);
		
		//重定向到订单列表
		response.sendRedirect(request.getContextPath()+"/manager/OrderManagerServlet?method=orderList");
		
	}
	
	/**
	 * 获取所有的订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取所有订单的列表
		List<Order> list = orderService.getOrderList();
		
		//将list放入到request域中
		request.setAttribute("list", list);
		
		//转发到/pages/manager/order_manager.jsp
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
		
	}

}
