package com.atguigu.bookstore.bean;

import java.util.List;

/**
 * 保存分页信息的类
 * 
 * @author lilichao
 * 
 * @param <T>
 */
public class Page<T> {

	/**
	 * 当前页码，需要在Servlet获取
	 */
	private int pageNumber;
	
	/**
	 * 每页显示数据的条数，在Servlet中获取
	 */
	private int pageSize;
	
	/**
	 * 总记录数，通过查询数据库获得
	 */
	private int totalRecord;
	
	/**
	 * 开始的索引，通过计算获得，在get方法中计算
	 * 
	 * SELECT * FROM bs_book LIMIT index , pageSize
	 */
	//private int index;
	
	/**
	 * 总页数，通过计算获得
	 */
	//private int totalPage;
	
	/**
	 * 分页的数据，通过查询数据库获得
	 */
	private List<T> data;
	
	/**
	 * 保存分页的请求地址
	 */
	private String path;

	public int getPageNumber() {
		
		//判断pageNumber是否小于1
		if(pageNumber < 1){
			return 1;
		}
		
		//判断pageNumber是否大于totalPage
		if(pageNumber > getTotalPage()){
			return getTotalPage();
		}
		
		
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getIndex() {
		
		/**
		 * 计算index的值
		 * 
		 * index      pageNumber       pageSize
		 * 	 0			  1				  3
		 * 	 3			  2  			  3	
		 *   6        	  3				  3
		 * 
		 * 规律
		 * 	index = (pageNumber-1)*pageSize
		 * 
		 */
		
		return (getPageNumber()-1)*getPageSize();
	}

	/*public void setIndex(int index) {
		this.index = index;
	}*/

	public int getTotalPage() {
		
		/**
		 * 在这里计算总页数
		 * 
		 * 		totalPage      totalRecord       pageSize
		 * 			5				10				2
		 * 			5				9				2
		 * 			4				8				2
		 * 			4				7				2
		 * 
		 * 		totalPage = totalRecord / pageSize (可以整除)
		 * 
		 * 		totalPage = totalRecord / pageSize +1 (有余数)
		 * 
		 */
		
		//如果总记录数除以每页显示的条数，可以整除
		if(getTotalRecord()%getPageSize() == 0){
			return getTotalRecord()/getPageSize();
		}else{
			
			//不可以整除
			return getTotalRecord()/getPageSize()+1;
		}
		
		
	}

	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
