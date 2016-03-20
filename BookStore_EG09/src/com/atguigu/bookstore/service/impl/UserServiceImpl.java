package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

/**
 * UserService的实现类
 * @author lilichao
 *
 */
public class UserServiceImpl implements UserService {
	
	//创建一个UserDao
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		
		int count = userDao.saveUser(user);
		
		return count == 1;
	}

	@Override
	public boolean checkUsername(String username) {
		
		User user = userDao.getUserByUsername(username);
		
		return user == null;
	}

}
