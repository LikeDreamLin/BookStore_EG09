package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 定义用户相关业务的接口
 * @author lilichao
 *
 */
public interface UserService {
	
	/**
	 * 处理登录业务，如果返回的null则登录失败，如果返回一个User对象则登录成功
	 * @param user
	 * @return
	 */
	User login(User user);
	
	/**
	 * 处理注册业务，如果返回true则注册成功，否则用户名已存在
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	
	/**
	 * 检查用户名是否存在
	 * 如果存在返回一个false  用户名不可用
	 * 如果不存在返回一个true  用户名可用
	 * @param username
	 * @return
	 */
	boolean checkUsername(String username);

}
