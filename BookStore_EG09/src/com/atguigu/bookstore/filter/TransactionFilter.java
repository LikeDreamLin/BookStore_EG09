package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.util.JDBCUtils;

/**
 * 处理事务的Filter
 * @author lilichao
 *
 */
public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			//放行请求
			chain.doFilter(request, response);
			
			//提交事务
			conn.commit();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//出现异常回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			//转发到错误页面
			request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
		}finally{
			//关闭数据库连接
			JDBCUtils.releaseConnection();
		}

	}

}
