package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;

public class TestCart {
	
	@Test
	public void test2(){
		//创建一个购物车
		Cart cart = new Cart();
		
		//创建几本图书
		Book book = new Book(1, "aa", "aa", 0.05, 100, 100, "hello");
		Book book2 = new Book(2, "bb", "aa", 0.01, 100, 100, "hello");
		
		//向购物车中添加两本书
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book);
		
		
		
		//输出购物车的信息
		System.out.println("商品总数量: "+cart.getTotalCount());
		System.out.println("商品总金额: "+cart.getTotalAmount());
		
	}

	@Test
	public void test() {
		
		//创建一个购物车
		Cart cart = new Cart();
		
		//创建几本图书
		Book book = new Book(1, "aa", "aa", 10.0, 100, 100, "hello");
		Book book2 = new Book(2, "bb", "aa", 9.0, 100, 100, "hello");
		Book book3 = new Book(3, "bb", "aa", 5.0, 100, 100, "hello");
		
		//向cart中添加一个商品
		cart.addBook2Cart(book);
		cart.addBook2Cart(book);
		cart.addBook2Cart(book);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);
		
		//移除一本书
		cart.delCartItem("1");
		
		//修改图书的数量
		cart.updateCount("3", "10");
		
		//清空购物车
		cart.clear();
		
		
		//输出购物车的信息
		System.out.println("商品总数量: "+cart.getTotalCount());
		System.out.println("商品总金额: "+cart.getTotalAmount());
		
	}

}
