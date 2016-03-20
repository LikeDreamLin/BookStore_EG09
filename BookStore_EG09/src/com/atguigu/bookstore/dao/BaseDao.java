package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.util.JDBCUtils;

/**
 * 定义数据库操作的基本的方法
 * @author lilichao
 *
 * @param <T>
 */
public class BaseDao<T> {
	
	//获取QueryRunner
	private QueryRunner runner = new QueryRunner();
	
	//泛型的类型
	private Class<T> type;
	
	//这个构造器会在子类中调用
	public BaseDao() {
		
		//获取父类的类型
		//UserDaoImpl extends BaseDao<User>
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		//获取父类的泛型的类型
		Type[] types = pt.getActualTypeArguments();
		
		//设置泛型的类型
		this.type = (Class<T>) types[0];
		
	}
	
	/**
	 * 用来做批处理操作的方法
	 * @param sql
	 * @param params
	 * 
	 * QueryRunner的batch方法需要三个参数
	 * 	数据库连接 Connection
	 *  SQL语句 要执行的SQL
	 *  Object二维数组
	 *  	既然是批处理SQL是要执行多次的，SQL语句中是有占位符，每次执行时替换占位符的参数都是不同
	 *  	二维数组的第一维代表的是SQL执行的次数。
	 *      二维数组的第二维是每次执行时所使用的参数
	 */
	public void batch(String sql , Object[][] params){
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			//将SQLExcepiton转换为RuntimeException
			throw new RuntimeException(e);
		} finally{
			//JDBCUtils.releaseConnection(conn);
		}
		
		
	}
	
	/**
	 * 查询到一个单个的值，主要用来执行类似：
	 * 	SELECT COUNT(*) FROM XXX 这样的SQL语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql , Object ... params){
		
		//创建一个Object
		Object obj = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			obj = runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			//将SQLExcepiton转换为RuntimeException
			throw new RuntimeException(e);
		} finally{
			//释放数据库连接
			//JDBCUtils.releaseConnection(conn);
		}
		
		return obj;
		
		
	}
	
	/**
	 * 查询一组对象的List
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql , Object ... params){
		
		//定义一个List
		List<T> list = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			list = runner.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			//将SQLExcepiton转换为RuntimeException
			throw new RuntimeException(e);
		} finally{
			//JDBCUtils.releaseConnection(conn);
		}
		
		
		return list;
		
	}
	
	
	
	/**
	 * 查询一个对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql , Object ... params){
		
		//定义一个变量
		T t = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		//执行查询数据库
		try {
			t = runner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			//将SQLExcepiton转换为RuntimeException
			throw new RuntimeException(e);
		} finally{
			//JDBCUtils.releaseConnection(conn);
		}
		
		return t;
		
	}
	
	/**
	 * 更新数据库的方法，增删改都找他
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql , Object ... params){
		
		//定义一个数量
		int count = 0;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			//执行更新操作
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
			
			//将SQLExcepiton转换为RuntimeException
			throw new RuntimeException(e);
			
		} finally{
			//释放数据库连接
			//JDBCUtils.releaseConnection(conn);
		}
		
		return count;
		
	}
	

}
