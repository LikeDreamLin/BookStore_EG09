package com.atguigu.bookstore.servlet.manager;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理管理的图书请求的Servlet
 * @author lilichao
 *
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建BookService的实例
	private BookService bookService = new BookServiceImpl();

	
	/**
	 * 分页查找图书的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取页码
		String pageNumber = request.getParameter("pageNumber");
		
		//指定一个pageSize
		int pageSize = 4;
		
		//调用Service查询分页数据
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		
		//获取请求的路径
		String path = WEBUtils.getPath(request);
		
		//设置page的path属性
		page.setPath(path);
		
		//将page保存到request域中
		request.setAttribute("page", page);
		
		//转发到jsp页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
		
	}
	
	
	/**
	 * 更新图书的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户填写的图书信息
		Book book = WEBUtils.param2Bean(request, new Book());
		
		//判断book中id是否为0
		if(book.getId()== null || book.getId() == 0){
			//添加图书
			bookService.saveBook(book);
		}else{
			//修改图书
			bookService.updateBook(book);
			
		}
		
		//获取referer请求头
		String referer = request.getParameter("referer");
		
		//重定向到图书列表页面
		//response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=findBook");
		response.sendRedirect(referer);
		
		
		
	}
	
	/**
	 * 转到更新数据的页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要修改的图书的id
		String bookId = request.getParameter("bookId");
		
		//调用service查询图书信息
		Book book = bookService.getBookById(bookId);
		
		//将book保存到request域中
		request.setAttribute("book", book);
		
		//转发到编辑页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
		
		
	}
	
	/**
	 * 删除图书的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要删除的图书的id
		String bookId = request.getParameter("bookId");
		
		//调用service删除图书
		bookService.delBook(bookId);
		
		//获取referer请求头
		String referer = request.getHeader("referer");
		
		
		
		//重定向到/manager/BookManagerServlet?method=bookList
		//response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=findBook");
		
		//重定向到referer指向的地址
		response.sendRedirect(referer);
		
	}
	
	
	/**
	 * 向数据库中添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		//获取到用户填写的图书信息，并将它们封装为一个Book对象
		Book book = WEBUtils.param2Bean(request, new Book());
		
		//将图书添加到数据库
		bookService.saveBook(book);
		
		//重定向到/manager/BookManagerServlet?method=bookList
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=findBook");
		
		
		
	}
	
	/**
	 * 查询数据库中所有的图书，并显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void bookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//查询所有的图书的列表
		List<Book> list = bookService.getBookList();
		
		//将list保存到
		request.setAttribute("list", list);
		
		//转发到/pages/manager/book_manager.jsp
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}

}
