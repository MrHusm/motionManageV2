package com.manage.model;

import java.util.Date;

public class Recharge_Repay implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="recharge_repay";
	
	private String id;

	private String name; //名称
	
	private String rechargeType;//充值方式 1短信 2 支付宝 3充值卡 4银行卡
	
	private String repayType;//赠送类型 1铜币 2 代金券
	
	private String activityType;//活动类型 1默认 2 活动
	
	private String isCashMoney;//是否使用代金券金额 1 使用 2 不使用
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期	
	
	private String version;//版本号
	
	private String channelType;//渠道限制
	
	private String channelIds; //渠道Ids
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间
	
	private Integer maxMoney;//赠送金额最大值
	
	private String content;//活动文案
	
	private String firstContent;//首页文案
	
	private String firstFlag;//首充标志 0非首充 1首充

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

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
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

	public String getIsCashMoney() {
		return isCashMoney;
	}

	public void setIsCashMoney(String isCashMoney) {
		this.isCashMoney = isCashMoney;
	}

	public Integer getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Integer maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstContent() {
		return firstContent;
	}

	public void setFirstContent(String firstContent) {
		this.firstContent = firstContent;
	}
}