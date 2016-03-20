package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装购物车信息的类
 * 
 * @author lilichao
 * 
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 保存购物项的map，key是book的id，value就是对应的CartItem
	 */
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

	/**
	 * 购物车中商品的总数量
	 * 通过计算获得
	 */
	//private int totalCount;

	/**
	 * 购物车中商品的总金额
	 * 通过计算获得
	 */
	//private double totalAmount;
	
	public void updateCount(String bookId , String countStr){
		
		//获取购物项
		CartItem cartItem = map.get(bookId);
		
		try{
			//将countStr转换为int类型
			int count = Integer.parseInt(countStr);
			//设置新的数量
			cartItem.setCount(count);
			
		}catch(Exception e){
			
		}
		
		
		
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		
		//清空map
		map.clear();
		
	}
	
	
	/**
	 * 删除一个购物项
	 * @param bookId
	 */
	public void delCartItem(String bookId){
		
		//从map中移除一个购物项
		map.remove(bookId);
		
	}
	
	
	/**
	 * 向购物车中添加一本图书
	 * @param book
	 */
	public void addBook2Cart(Book book){
		
		//从购物车中获取购物项
		CartItem cartItem = map.get(book.getId()+"");
		
		//判断CartItem是否为null
		if(cartItem == null){
			//创建一个新的CartItem
			cartItem = new CartItem();
			//设置购物项的图书信息
			cartItem.setBook(book);
			//设置购物项的数量
			cartItem.setCount(1);
			//将CartItem放入到map中
			map.put(book.getId()+"", cartItem);
			
		}else{
			//获取购物项的数量
			int count = cartItem.getCount();
			//购物项的数量+1
			cartItem.setCount(count+1);
			
		}
		
	}
	
	
	
	/**
	 * 获取购物车中的所有的购物项
	 * @return
	 */
	public List<CartItem> getCartItems(){
		
		//获取map中所有的value
		Collection<CartItem> values = map.values();
		
		//将collection转换为一个ArrayList
		return new ArrayList<CartItem>(values);
		
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public int getTotalCount() {
		
		//计算商品的总数量
		int totalCount = 0;
		
		//获取所有的CartItem
		List<CartItem> cartItems = getCartItems();
		//遍历cartItems
		for (CartItem cartItem : cartItems) {
			//计算中数量
			totalCount += cartItem.getCount();
		}
		
		
		return totalCount;
	}


	public double getTotalAmount() {
		
		//计算商品的总金额
		//double totalAmount = 0;
		BigDecimal totalAmount = new BigDecimal("0");
		
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		//遍历购物项
		for (CartItem cartItem : cartItems) {
			//计算总金额
			BigDecimal amount = new BigDecimal(cartItem.getAmount()+"");
			
			totalAmount = totalAmount.add(amount);
		}
		
		return totalAmount.doubleValue();
	}
	
	


}
