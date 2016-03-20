package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 定义图书相关的数据库操作的方法
 * @author lilichao
 *
 */
public interface BookDao {

	/**
	 * 向数据库中插入一本图书
	 * @param book
	 * @return
	 */
	int saveBook(Book book);
	
	/**
	 * 根据id删除一本图书
	 * @param bookId
	 * @return
	 */
	int delBook(String bookId);
	
	/**
	 * 修改一本图书的信息
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
	
	/**
	 * 根据id查找一本图书的信息
	 * @param bookId
	 * @return
	 */
	Book getBookById(String bookId);
	
	/**
	 * 查找所有图书
	 * @return
	 */
	List<Book> getBookList();
	
	/**
	 * 分页查找图书的信息
	 * @param page
	 * @return
	 */
	Page<Book> findBook(Page<Book> page);
	
	/**
	 * 根据价格查找分页信息
	 * @param page
	 * @param min
	 * @param max
	 * @return
	 */
	Page<Book> findBookByPrice(Page<Book> page , double min , double max);
	
	/**
	 * 批量修改图书的销量和库存
	 * @param params
	 */
	void batchUpdateSalesAndStock(Object[][] params);
	
}
