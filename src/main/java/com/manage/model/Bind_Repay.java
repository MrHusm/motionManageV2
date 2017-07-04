package com.manage.model;

import java.util.Date;

public class Bind_Repay implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="bind_repay";
	
	private String id;

	private String name; //名称
	
	private String bindType;//绑定类型 1手机 2 QQ 3微博
	
	private String repayType;//赠送类型 1铜币 2 代金券
	
	private String cashCouponId;//代金劵 id
	
	private String isCashMoney;//是否使用代金券金额 1 使用 2 不使用
	
	private String money; //赠送金额（单位：铜币）
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期	
	
	private String version;//版本号
	
	private String channelType;//渠道限制
	
	private String channelIds; //渠道Ids
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间
	
	private String byType;//是否赠送包月 1 不赠送 2 赠送
	
	private String byId;//包月ID
	
	private String day;//赠送天数
	
	private String content;//顶部文案

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

	public String getCashCouponId() {
		return cashCouponId;
	}

	public void setCashCouponId(String cashCouponId) {
		this.cashCouponId = cashCouponId;
	}

	public String getIsCashMoney() {
		return isCashMoney;
	}

	public void setIsCashMoney(String isCashMoney) {
		this.isCashMoney = isCashMoney;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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

	public String getByType() {
		return byType;
	}

	public void setByType(String byType) {
		this.byType = byType;
	}

	public String getById() {
		return byId;
	}

	public void setById(String byId) {
		this.byId = byId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
