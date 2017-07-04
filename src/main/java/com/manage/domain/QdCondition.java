package com.manage.domain;

import java.util.Date;

public class QdCondition extends Condition {
	
	private int id;
	
	private String name;//驱动名称
	
	private int categoryId;//图书分类
	
	private int authoryId;//作者分类
	
	private Date startDate;//时间段 开始
	
	private Date endDate;//时间段 结束

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getAuthoryId() {
		return authoryId;
	}

	public void setAuthoryId(int authoryId) {
		this.authoryId = authoryId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
