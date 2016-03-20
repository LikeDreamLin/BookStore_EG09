package junit.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.util.JDBCUtils;

public class TestJDBCUtils {

	@Test
	public void testJDBCUtils() {
		
		Connection conn = JDBCUtils.getConnection();
		
		System.out.println(conn);
		
		JDBCUtils.releaseConnection(conn);
		
	}

}
