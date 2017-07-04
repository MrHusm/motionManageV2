package com.manage.domain;

public class JpBannerCondition extends Condition{
	private Integer bpId;//广告位ID
	
	private String name;//榜单名称
	
	private Integer type;//广告类型
	
	private String source="0";//0 中文书城 1 创新版

	public Integer getBpId() {
		return bpId;
	}

	public void setBpId(Integer bpId) {
		this.bpId = bpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
