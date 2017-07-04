package com.manage.domain;

public class BindCondition extends Condition{
	
	private String name; //名称
	
	private String bindType;//绑定类型 1手机 2 QQ 3微博 4微信

	private String repayType;//赠送类型 1铜币 2 代金券
	
	private String channelId; //渠道
	
	private String money; //赠送金额（单位：铜币）
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期
	
	private String byType;//是否赠送包月 1 不赠送 2 赠送
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getByType() {
		return byType;
	}

	public void setByType(String byType) {
		this.byType = byType;
	}
}
