package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookService {
	
	BookService bookService = new BookServiceImpl();
	
	@Test
	public void test2(){
		Page<Book> page = bookService.findBookByPrice("1", 4, "abc", "bcd");
		
		List<Book> data = page.getData();
		
		for (Book book : data) {
			System.out.println(book);
		}
	}

	@Test
	public void test() {
		
		Page<Book> page = bookService.findBook("100000", 4);
		
		List<Book> data = page.getData();
		
		for (Book book : data) {
			System.out.println(book);
		}
	}

}
