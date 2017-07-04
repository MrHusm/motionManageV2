package com.manage.domain;

public class JpBannerPositionCondition extends Condition{
	private String name;//广告位名称
	
	private String source="0";//0 中文书城 1 创新版

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
