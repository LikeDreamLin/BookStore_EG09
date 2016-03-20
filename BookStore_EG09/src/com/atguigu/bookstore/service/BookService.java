package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 定义图书相关的业务逻辑的接口
 * 
 * @author lilichao
 * 
 */
public interface BookService {

	/**
	 * 添加图书的业务
	 * 
	 * @param book
	 * @return
	 */
	int saveBook(Book book);

	/**
	 * 删除图书
	 * 
	 * @param bookId
	 * @return
	 */
	int delBook(String bookId);

	/**
	 * 修改图书
	 * 
	 * @param book
	 * @return
	 */
	int updateBook(Book book);

	/**
	 * 查找一本图书的信息
	 * 
	 * @param bookId
	 * @return
	 */
	Book getBookById(String bookId);

	/**
	 * 查找所有图书
	 * 
	 * @return
	 */
	List<Book> getBookList();

	/**
	 * 分页查找图书
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Book> findBook(String pageNumber, int pageSize);

	/**
	 * 根据价格分页查找图书
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Book> findBookByPrice(String pageNumber, int pageSize, String min, String max);

}
