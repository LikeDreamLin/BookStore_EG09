package junit.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;

public class TestOrderDao {
	
	OrderDao orderDao = new OrderDaoImpl();

	@Test
	public void testSaveOrder() {
		
		
		
		//获取一个用户的id
		int userId = 4;
		//生成一个订单号，时间戳+""+userId
		String id = System.currentTimeMillis() +"" + userId;
		//创建一个Order对象
		Order order = new Order(id, new Date(), 2, 20, 0, userId);
		
		//将order插入进数据库
		int count = orderDao.saveOrder(order);
		
		System.out.println(count);
		
		
		
	}

	@Test
	public void testGetOrderList() {
		
		List<Order> orderList = orderDao.getOrderList();
		
		System.out.println(orderList);
		
	}

	@Test
	public void testGetOrderListByUserId() {
		
		List<Order> list = orderDao.getOrderListByUserId("4");
		
		System.out.println(list);
		
	}

	@Test
	public void testUpdateOrderState() {
		
		orderDao.updateOrderState("14512693798774", 1);
	}

}
