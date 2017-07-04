package com.manage.domain;

public class BannerAppCondition extends Condition{
	
	private String pageType;//1 应用推荐页 2每日福利页
	
	private String name;//名称
	
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
