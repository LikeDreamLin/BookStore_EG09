package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

/**
 * OrderService的实现类
 * @author lilichao
 *
 */
public class OrderServiceImpl implements OrderService {
	
	//创建三个Dao
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao itemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	

	@Override
	public String createOrder(Cart cart, User user) {
		
		
		//获取用户的id
		int userId = user.getId();
		//生成订单号
		String orderId = System.currentTimeMillis()+""+userId;
		//获取商品的总数量
		int totalCount = cart.getTotalCount();
		//获取商品的总金额
		double totalAmount = cart.getTotalAmount();
		
		//创建一个Order
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, userId);
		//将order插入进数据库
		orderDao.saveOrder(order);
		
		//获取所有的购物项
		List<CartItem> cartItems = cart.getCartItems();
		
		//定义两个二维数组
		Object[][] itemParams = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];
		
		
		//遍历CartItems
		for (int i=0 ; i<cartItems.size() ; i++) {
			
			CartItem cartItem = cartItems.get(i);
			
			//获取商品数量
			int count = cartItem.getCount();
			//获取商品的金额
			double amount = cartItem.getAmount();
			//获取图书
			Book book = cartItem.getBook();
			//获取图书信息
			String title = book.getTitle();
			String author = book.getAuthor();
			Double price = book.getPrice();
			String imgPath = book.getImgPath();
			
			//获取图书的销量及库存
			Integer sales = book.getSales();
			Integer stock = book.getStock();
			
			
			//设置OrderItem的参数
			//count, amount, title, author, price, img_path, order_id
			itemParams[i] = new Object[]{count,amount,title,author,price,imgPath,orderId};
			
			//如果库存小于0
			if(stock-count < 0){
				//抛出一个异常
				throw new RuntimeException("库存不足");
			}
			
			//设置book的参数
			//sales=? , stock=? WHERE id=?
			bookParams[i] = new Object[]{sales+count, stock-count, book.getId()};
			
		}
		
		//执行批处理
		bookDao.batchUpdateSalesAndStock(bookParams);
		itemDao.batchSaveOrderItem(itemParams);
		
		//订单已经生成，清空购物车
		cart.clear();
		
		
		return orderId;
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		
		return orderDao.getOrderListByUserId(userId);
	}

	@Override
	public List<OrderItem> getOrderItemList(String orderId) {
		return itemDao.getOrderItemList(orderId);
	}

	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}

	@Override
	public void sendBook(String orderId) {
		
		orderDao.updateOrderState(orderId, 1);
		
	}

	@Override
	public void takeBook(String orderId) {
		
		orderDao.updateOrderState(orderId, 2);
		
	}

}
