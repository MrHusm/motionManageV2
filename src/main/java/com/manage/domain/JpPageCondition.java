package com.manage.domain;

public class JpPageCondition extends Condition{
	
	private String pageType; //页面 1 重磅 2 男频 3 女频 4 出版物
	
	private String source="0";//0 中文书城  1创新版
	
	private String orderKey;

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
