package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

/**
 * 定义订单项相关数据库操作的接口
 * @author lilichao
 *
 */
public interface OrderItemDao {
	
	/**
	 * 向数据库中插入一个订单项
	 * @param orderItem
	 * @return
	 */
	int saveOrderItem(OrderItem orderItem);
	
	
	/**
	 * 根据订单号，获取当前订单的所有订单项
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemList(String orderId);
	
	
	/**
	 * 批量插入订单项
	 * @param params
	 */
	void batchSaveOrderItem(Object[][] params);
	
	
	

}
