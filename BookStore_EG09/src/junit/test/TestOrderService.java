package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class TestOrderService {
	
	OrderService orderService = new OrderServiceImpl();
	BookDao bookDao = new BookDaoImpl();

	@Test
	public void testCreateOrder() {
		
		//创建一个Cart
		Cart cart = new Cart();
		
		//获取三本书
		Book book1 = bookDao.getBookById("14");
		Book book2 = bookDao.getBookById("15");
		Book book3 = bookDao.getBookById("16");
		
		//将三本书添加到购物车中
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);
		
		//创建一个User
		User user = new User(4, null, null, null);
		
		//生成订单
		String orderId = orderService.createOrder(cart, user);
		
		System.out.println(orderId);
		
		
	}

}
