package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class TestOrderItem {
	
	//创建OrderItemDao
	OrderItemDao dao = new OrderItemDaoImpl();

	@Test
	public void testSaveOrderItem() {
		
		OrderItem orderItem = new OrderItem(null, 2, 10, "三国演义", "老罗", 5, "hello", "145126937987741");
		
		
		//将orderItem存储到数据库
		dao.saveOrderItem(orderItem);
		
	}
	
	@Test
	public void testGetOrderItemList(){
		List<OrderItem> list = dao.getOrderItemList("14512693798774");
		
		System.out.println(list);
	}

}
