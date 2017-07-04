package com.manage.domain;

public class CodeCondition extends Condition{
	
	private String name; //名称
	
	private String type;//类型 0公共兑换码 1单一兑换码 2所有

	private String code; //兑换码
	
	private String channelId; //渠道
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期
	
	private String ecrId;//单一 兑换码ID
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEcrId() {
		return ecrId;
	}

	public void setEcrId(String ecrId) {
		this.ecrId = ecrId;
	}
}
