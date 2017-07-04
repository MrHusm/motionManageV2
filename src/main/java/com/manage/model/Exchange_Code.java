package com.manage.model;

import java.util.Date;

public class Exchange_Code implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="exchange_code";
	
	private String id;
	
	private String channelId;//渠道
	
	private String name;//名称
	
	private String code;//兑换码
	
	private String cc;//兑换次数
	
	private String userCc;//用户兑换个数
	
	private String exchgNum;//已兑换次数
	
	private String telFlag;//是否验证绑定手机号 0不验证 1验证
	
	private String imsiFlag;//是否验证 imsi 0不验证 1验证
	
	private String startDate;//使用期限
	
	private String endDate;//使用期限
	
	private String cashCouponId;//代金劵 id

	private String money;//铜币

	private String chpIds;//单章

	private String bookIds;//全本id
	
	private String byIds;//包月id
	
	private String ecrId;//0公开, 大于0非公开（exchange_code_rule的id）
	
	private String vipDay;
	
	private String channelType;
	
	private String channelIds;//0 无 1,仅在  2仅不在
	
	private Date createDate;
	
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getUserCc() {
		return userCc;
	}

	public void setUserCc(String userCc) {
		this.userCc = userCc;
	}

	public String getTelFlag() {
		return telFlag;
	}

	public void setTelFlag(String telFlag) {
		this.telFlag = telFlag;
	}

	public String getImsiFlag() {
		return imsiFlag;
	}

	public void setImsiFlag(String imsiFlag) {
		this.imsiFlag = imsiFlag;
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

	public String getCashCouponId() {
		return cashCouponId;
	}

	public void setCashCouponId(String cashCouponId) {
		this.cashCouponId = cashCouponId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getChpIds() {
		return chpIds;
	}

	public void setChpIds(String chpIds) {
		this.chpIds = chpIds;
	}

	public String getBookIds() {
		return bookIds;
	}

	public void setBookIds(String bookIds) {
		this.bookIds = bookIds;
	}

	public String getByIds() {
		return byIds;
	}

	public void setByIds(String byIds) {
		this.byIds = byIds;
	}

	public String getEcrId() {
		return ecrId;
	}

	public void setEcrId(String ecrId) {
		this.ecrId = ecrId;
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

	public String getVipDay() {
		return vipDay;
	}

	public void setVipDay(String vipDay) {
		this.vipDay = vipDay;
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

	public String getExchgNum() {
		return exchgNum;
	}

	public void setExchgNum(String exchgNum) {
		this.exchgNum = exchgNum;
	}
}
