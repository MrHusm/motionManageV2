package com.manage.domain;

public class RechargeCondition extends Condition{
	
	private String name; //名称
	
	private String rechargeType;//充值方式 1短信 2 支付宝 3充值卡 4银行卡

	private String channelId; //渠道

	private String repayType;//赠送类型 1铜币 2 代金券
	
	private String activityType; //活动类型 1默认 2 活动
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期
	
	private String firstFlag;//首充标志 0非首充 1首充
	
	
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

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}
}
