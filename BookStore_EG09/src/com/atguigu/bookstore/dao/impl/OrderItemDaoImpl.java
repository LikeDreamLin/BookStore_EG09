package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

/**
 * OrderItemDao的实现类
 * 
 * @author lilichao
 * 
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {

		String sql = "INSERT INTO bs_order_item(count, amount, title, author, price, img_path, order_id) "
				+ "VALUES(?,?,?,?,?,?,?)";

		return this.update(sql, orderItem.getCount(), orderItem.getAmount(),
				orderItem.getTitle(), orderItem.getAuthor(),
				orderItem.getPrice(), orderItem.getImgPath(),
				orderItem.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemList(String orderId) {

		String sql = "SELECT id, count, amount, title, author, price, img_path imgPath, order_id orderId "
				+ "FROM bs_order_item WHERE order_id=?";

		return this.getBeanList(sql, orderId);
	}

	@Override
	public void batchSaveOrderItem(Object[][] params) {
		
		String sql = "INSERT INTO bs_order_item(count, amount, title, author, price, img_path, order_id) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		this.batch(sql, params);
		
		
	}

}
