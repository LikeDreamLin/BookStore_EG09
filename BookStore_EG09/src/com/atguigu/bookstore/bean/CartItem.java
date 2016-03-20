package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 封装购物项信息的类
 * 
 * @author lilichao
 * 
 */
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 购物项的图书的信息
	 */
	private Book book;

	/**
	 * 图书的数量
	 */
	private int count;

	/**
	 * 图书的金额 需要通过计算获得
	 */
	// private double amount;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {

		// 金额 = 数量*单价
		BigDecimal count = new BigDecimal(getCount()+"");
		BigDecimal price = new BigDecimal(book.getPrice()+"");

		return count.multiply(price).doubleValue();
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}
