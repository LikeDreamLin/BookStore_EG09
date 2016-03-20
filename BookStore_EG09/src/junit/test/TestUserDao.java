package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDao {
	
	//创建UserDao对象
	UserDao userDao = new UserDaoImpl();

	@Test
	public void testGetUserByUsernameAndPassword() {
		
		//创建一个User
		User user = new User(null, "admin", "123456", null);
		
		//调用DAO
		User loginUser = userDao.getUserByUsernameAndPassword(user);
		
		System.out.println(loginUser);
		
	}

	@Test
	public void testSaveUser() {
		
		//创建一个User对象
		User user = new User(null, "admin", "123123", "ad@ad.com");
		
		//调用DAO将用户插入进数据库
		int count = userDao.saveUser(user);
		
		System.out.println(count);
		
	}

}
