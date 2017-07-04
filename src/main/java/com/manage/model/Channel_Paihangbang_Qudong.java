package com.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Channel_Paihangbang_Qudong implements Serializable {

	static final String TABLE_NAME ="channel_paihangbang_qudong";
	
	private int id;
	
	private String name;//驱动名称
	
	private int categoryId;//图书分类
	
	private int authoryId;//作者分类
	
	private int qdType;//驱动类型 1图书驱动 2作者驱动
	
	private int free;//是否收费  1不限 2收费 3不收费
	
	private int status;//是否完结 1不限 2完结 3未完结
	
	private String startDate;//时间段 开始
	
	private String endDate;//时间段 结束
	
	private int rule;//排序规则
	
	private Date createDate;//创建驱动时间
	
	private Date updateDate;//修改驱动时间
	
	private String category;//图书分类
	
	private String authory;//作者分类

	private int twoId;
	
	private int datee;
	
	
	
	
	
	
	
	public int getDatee() {
		return datee;
	}

	public void setDatee(int datee) {
		this.datee = datee;
	}

	public int getTwoId() {
		return twoId;
	}

	public void setTwoId(int twoId) {
		this.twoId = twoId;
	}


	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthory() {
		return authory;
	}

	public void setAuthory(String authory) {
		this.authory = authory;
	}

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

	public int getQdType() {
		return qdType;
	}

	public void setQdType(int qdType) {
		this.qdType = qdType;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
}
