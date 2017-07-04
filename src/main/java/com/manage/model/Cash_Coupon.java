package com.manage.model;

import java.util.Date;

public class Cash_Coupon implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="cash_coupon";
	
	private String id;
	
	private String name; //名称
	
	private String money; //金额
	
	private String discount; //折扣
	
	private String channelId;//渠道ID
	
	private String couponType;//代金券类型
	
	private String type; //类型 0不固定使用期限 1 固定使用期限
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期
	
	private String day; //有效的天数
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	
}
