package com.manage.domain;

public class CouponCondition extends Condition{
	
	private String name; //名称
	
	private String money; //金额
	
	private String type;//类型 0不固定使用期限 1 固定使用期限
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期
	
	private String day;//有效期天数
	
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
	
}
