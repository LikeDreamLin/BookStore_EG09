package com.atguigu.bookstore.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接的工具类
 * @author lilichao
 *
 */
public class JDBCUtils {
	
	//获取数据源
	private static DataSource dataSource = new ComboPooledDataSource("webDataSource");
	
	//定义一个静态的Connection
	//private static Connection conn;
	
	//创建一个Map来保存数据库连接
	//private static Map<Thread, Connection> map = new ConcurrentHashMap<Thread, Connection>();
	
	//创建一个ThreadLocal对象
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		
		
		//从ThreadLocal获取当前线程的Connection
		Connection conn = threadLocal.get();
		
		//判断conn是否为null
		if(conn == null){
			//获取数据库连接
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//将conn保存到ThreadLocal中
			threadLocal.set(conn);
		}
		
		//返回conn
		return conn;
		
		
		
		/*//获取当前线程的数据库连接
		Connection conn = map.get(Thread.currentThread());
		
		//判断conn是否为null
		if(conn == null){
			//创建一个新的数据库连接
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//将conn放入到map中
			map.put(Thread.currentThread(), conn);
			
		}
		
		//返回conn
		return conn;
		*/
		
		
		
		/*//判断conn是否为null
		if(conn == null){
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return conn;*/
		
	}
	
	/**
	 * 释放数据库连接
	 */
	public static void releaseConnection(){
		
		//获取当前线程的Connection
		Connection conn = threadLocal.get();
		
		//关闭数据库连接
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//从ThreadLocal中移除conn
		threadLocal.remove();
		
		
		
		/*//获取当前线程的Connection
		Connection conn = map.get(Thread.currentThread());
		
		//关闭数据库连接
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//从map中移除数据库连接
			map.remove(Thread.currentThread());
		}*/
		
		
		
	/*	if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//将conn置空
		conn = null;*/
		
	}
	
	/**
	 * 释放数据库连接的方法
	 * @param conn
	 */
	public static void releaseConnection(Connection conn){
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
