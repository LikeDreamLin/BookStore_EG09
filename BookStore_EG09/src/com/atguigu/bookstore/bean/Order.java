package com.atguigu.bookstore.bean;

import java.util.Date;

/**
 * 封装订单信息的类
 * 
 * @author lilichao
 * 
 */
public class Order {

	/**
	 * 订单号 规则：时间戳+""+用户ID
	 */
	private String id;

	/**
	 * 订单生成的时间
	 */
	private Date orderTime;

	/**
	 * 商品的总数量
	 */
	private int totalCount;

	/**
	 * 商品的总金额
	 */
	private double totalAmount;

	/**
	 * 订单的状态： 0:未发货 1:已发货 2:交易完成
	 */
	private int state;

	/**
	 * 订单所属的用户的id
	 */
	private int userId;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String id, Date orderTime, int totalCount, double totalAmount,
			int state, int userId) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.state = state;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", totalCount="
				+ totalCount + ", totalAmount=" + totalAmount + ", state="
				+ state + ", userId=" + userId + "]";
	}

}
